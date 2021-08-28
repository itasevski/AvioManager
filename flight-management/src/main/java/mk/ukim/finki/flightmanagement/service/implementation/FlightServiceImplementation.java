package mk.ukim.finki.flightmanagement.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import mk.ukim.finki.flightmanagement.domain.model.exception.FlightNotFoundException;
import mk.ukim.finki.flightmanagement.domain.repository.FlightRepository;
import mk.ukim.finki.flightmanagement.service.FlightService;
import mk.ukim.finki.flightmanagement.service.form.FlightForm;
import mk.ukim.finki.flightmanagement.service.form.FlightParticipantForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class FlightServiceImplementation implements FlightService {

    private final FlightRepository flightRepository;
    private final Validator validator;

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
    public Flight findById(FlightId flightId) {
        return this.flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight with id " + flightId + " not found"));
    }

    @Override
    public void addFlightParticipant(FlightId flightId, FlightParticipantForm flightParticipantForm) {
        Flight flight = findById(flightId);
        flight.addFlightParticipant(flightParticipantForm.getPerson());
        this.flightRepository.saveAndFlush(flight);
    }

    @Override
    public void removeFlightParticipant(FlightId flightId, FlightParticipantId flightParticipantId) {
        Flight flight = findById(flightId);
        flight.removeFlightParticipant(flightParticipantId);
        this.flightRepository.saveAndFlush(flight);
    }

    private Flight toDomainObject(FlightForm flightForm) {
        Flight flight = new Flight(flightForm.getFlightDates(), flightForm.getDepartureCountry(), flightForm.getDestinationCountry(), flightForm.getPlaneId());
        flightForm.getItems().forEach(item -> flight.addFlightParticipant(item.getPerson()));

        return flight;
    }

}
