package mk.ukim.finki.flightmanagement.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.domain.model.exception.FlightNotFoundException;
import mk.ukim.finki.flightmanagement.domain.repository.FlightRepository;
import mk.ukim.finki.flightmanagement.domain.valueobject.*;
import mk.ukim.finki.flightmanagement.service.FlightService;
import mk.ukim.finki.flightmanagement.service.form.FlightForm;
import mk.ukim.finki.flightmanagement.service.form.FlightParticipantForm;
import mk.ukim.finki.flightmanagement.xhttp.client.CountryClient;
import mk.ukim.finki.flightmanagement.xhttp.client.PersonClient;
import mk.ukim.finki.flightmanagement.xhttp.client.PlaneClient;
import mk.ukim.finki.sharedkernel.domain.event.flight.FlightArrivedEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

/**
 * FlightServiceImplementation class - contains all implemented application services for the "flight-management" bounded context. All of the operations/services/methods
 * will be executed in a single transaction, hence the @Transactional annotation. This will ensure atomicity.
 */
@Service
@Transactional
@AllArgsConstructor
public class FlightServiceImplementation implements FlightService {

    private final FlightRepository flightRepository;
    private final Validator validator;
    private final CountryClient countryClient;
    private final PersonClient personClient;
    private final PlaneClient planeClient;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public FlightId createFlight(FlightForm flightForm) {
        Objects.requireNonNull(flightForm, "Flight form must not be null.");

        var violations = this.validator.validate(flightForm);
        if(violations.size() > 0) throw new ConstraintViolationException("The flight form is not valid.", violations);

        Flight flight = toDomainObject(flightForm);
        this.flightRepository.saveAndFlush(flight);

        return flight.getId();
    }

    @Override
    public List<Flight> findAll() {
        return this.flightRepository.findAll();
    }

    @Override
    public List<Flight> findByDepartureCountryIdOrDestinationCountryId(String countryId) {
        return this.flightRepository.findByDepartureCountryIdOrDestinationCountryId(CountryId.of(countryId), CountryId.of(countryId));
    }

    @Override
    public List<Flight> findByPlaneId(PlaneId planeId) {
        return this.flightRepository.findByPlaneId(planeId);
    }

    @Override
    public Optional<Flight> findById(FlightId flightId) {
        return this.flightRepository.findById(flightId);
    }

    @Override
    public Optional<Flight> updateById(FlightId flightId, FlightStatus flightStatus) {
        Optional<Flight> flight = findById(flightId);

        if(flight.isEmpty()) throw new FlightNotFoundException("Flight with id " + flightId + " does not exist.");

        flight.get().setFlightStatus(flightStatus);

        if(flightStatus == FlightStatus.ARRIVED) {
            Set<String> flightParticipantIds = new HashSet<>();

            for(FlightParticipant flightParticipant : flight.get().getFlightParticipants()) {
                flightParticipantIds.add(flightParticipant.getPersonId().getId());
            }

            this.domainEventPublisher.publish(new FlightArrivedEvent(flightParticipantIds));
        }

        return Optional.of(this.flightRepository.saveAndFlush(flight.get()));
    }

    @Override
    public void deleteById(FlightId flightId) {
        if(!this.flightRepository.existsById(flightId)) throw new FlightNotFoundException("Flight with id " + flightId + " does not exist.");
        this.flightRepository.deleteById(flightId);
    }

    @Override
    public Set<FlightParticipant> getFlightParticipantsByFlightId(FlightId flightId) {
        Optional<Flight> flight = findById(flightId);

        if(flight.isEmpty()) throw new FlightNotFoundException("Flight with id " + flightId + " does not exist.");

        return flight.get().getFlightParticipants();
    }

    @Override
    public void addFlightParticipant(FlightId flightId, FlightParticipantForm flightParticipantForm) {
        Person person = this.personClient.findById(flightParticipantForm.getPersonId());
        Optional<Flight> flight = findById(flightId);

        if(flight.isEmpty()) throw new FlightNotFoundException("Flight with id " + flightId + " does not exist.");

        flight.get().addFlightParticipant(person);

        this.flightRepository.saveAndFlush(flight.get());
    }

    @Override
    public void removeFlightParticipant(FlightId flightId, FlightParticipantId flightParticipantId) {
        Optional<Flight> flight = findById(flightId);

        if(flight.isEmpty()) throw new FlightNotFoundException("Flight with id " + flightId + " does not exist.");

        flight.get().removeFlightParticipant(flightParticipantId);

        this.flightRepository.saveAndFlush(flight.get());
    }

    @Override
    public void removeFlightParticipant(String personId) {
        List<Flight> flights = findAll();

        for(Flight flight : flights) {
            for(FlightParticipant flightParticipant : flight.getFlightParticipants()) {
                if(flightParticipant.getPersonId().getId().equals(personId)) {
                    removeFlightParticipant(flight.getId(), flightParticipant.getId());
                }
            }
        }
    }

    @Override
    public void removeDeletedCountry(String deletedCountryId, String notSpecifiedId) {
        List<Flight> flights = findByDepartureCountryIdOrDestinationCountryId(deletedCountryId);
        Country notSpecified = this.countryClient.findById(CountryId.of(notSpecifiedId));

        for(Flight flight : flights) {
            Flight updatedFlight = flight;
            if(updatedFlight.getDepartureCountryId().getId().equals(deletedCountryId)) {
                updatedFlight.setDepartureCountryId(CountryId.of(notSpecifiedId), notSpecified);
            }
            else {
                updatedFlight.setDestinationCountryId(CountryId.of(notSpecifiedId), notSpecified);
            }
            this.flightRepository.saveAndFlush(updatedFlight);
        }
    }

    @Override
    public void removeDeletedPlane(String deletedPlaneId, String notSpecifiedId) {
        List<Flight> flights = findByPlaneId(PlaneId.of(deletedPlaneId));
        Plane notSpecified = this.planeClient.findById(PlaneId.of(notSpecifiedId));

        for(Flight flight : flights) {
            Flight updatedFlight = flight;
            updatedFlight.setPlaneId(PlaneId.of(notSpecifiedId), notSpecified);
            this.flightRepository.saveAndFlush(updatedFlight);
        }
    }


    private Flight toDomainObject(FlightForm flightForm) {
        Country departureCountry = this.countryClient.findById(flightForm.getDepartureCountryId());
        Country destinationCountry = this.countryClient.findById(flightForm.getDestinationCountryId());
        Plane plane = this.planeClient.findById(flightForm.getPlaneId());

        Flight flight = new Flight(flightForm.getFlightDates(), flightForm.getDepartureCountryId(), departureCountry, flightForm.getDestinationCountryId(), destinationCountry, flightForm.getPlaneId(), plane);
        flightForm.getFlightParticipants().forEach(flightParticipant -> flight.addFlightParticipant(this.personClient.findById(flightParticipant.getPersonId())));

        return flight;
    }

}
