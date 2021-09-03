package mk.ukim.finki.rolemanagement.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.domain.model.*;
import mk.ukim.finki.rolemanagement.domain.model.exception.PersonNotFoundException;
import mk.ukim.finki.rolemanagement.domain.repository.FlightAttendantRepository;
import mk.ukim.finki.rolemanagement.domain.repository.PassengerRepository;
import mk.ukim.finki.rolemanagement.domain.repository.PilotRepository;
import mk.ukim.finki.rolemanagement.domain.valueobject.Country;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.rolemanagement.service.PersonService;
import mk.ukim.finki.rolemanagement.service.form.PersonForm;
import mk.ukim.finki.rolemanagement.xhttp.client.CountryClient;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.sharedkernel.domain.event.person.PersonDeletedEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

/**
 * PersonServiceImplementation class - contains all implemented application services for the "role-management" bounded context. All of the operations/services/methods
 * will be executed in a single transaction, hence the @Transactional annotation. This will ensure atomicity.
 */
@Service
@Transactional
@AllArgsConstructor
public class PersonServiceImplementation implements PersonService {

    private final FlightAttendantRepository flightAttendantRepository;
    private final PassengerRepository passengerRepository;
    private final PilotRepository pilotRepository;
    private final Validator validator;
    private final CountryClient countryClient;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public PersonId createPerson(PersonForm personForm) {
        Objects.requireNonNull(personForm, "Person form must not be null");

        var violations = this.validator.validate(validator);
        if(violations.size() > 0) throw new ConstraintViolationException("The person form is not valid.", violations);

        Person person = toDomainObject(personForm);

        if(person instanceof FlightAttendant) this.flightAttendantRepository.saveAndFlush((FlightAttendant) person);
        else if(person instanceof Passenger) this.passengerRepository.saveAndFlush((Passenger) person);
        else this.pilotRepository.saveAndFlush((Pilot) person);

        return person.getId();
    }

    @Override
    public List<Person> findAll() {
        final List<Person> people = new ArrayList<>();

        List<FlightAttendant> flightAttendants = this.flightAttendantRepository.findAll();
        List<Passenger> passengers = this.passengerRepository.findAll();
        List<Pilot> pilots = this.pilotRepository.findAll();

        people.addAll(flightAttendants);
        people.addAll(passengers);
        people.addAll(pilots);

        return people;
    }

    @Override
    public Optional<Person> findById(PersonId personId) {
        Person person;

        if(this.flightAttendantRepository.existsById(personId)) {
            person = this.flightAttendantRepository.findById(personId).get();
        }
        else if(this.passengerRepository.existsById(personId)) {
            person = this.passengerRepository.findById(personId).get();
        }
        else {
            person = this.pilotRepository.findById(personId).get();
        }

        return Optional.of(person);
    }

    @Override
    public void deleteById(PersonId personId) {
        Optional<Person> person = findById(personId);

        if(person.isEmpty()) throw new PersonNotFoundException("Person with id " + personId + " does not exist.");

        if(person.get() instanceof FlightAttendant) {
            this.flightAttendantRepository.delete((FlightAttendant) person.get());
        }
        else if(person.get() instanceof Passenger) {
            this.passengerRepository.delete((Passenger) person.get());
        }
        else {
            this.pilotRepository.delete((Pilot) person.get());
        }

        this.domainEventPublisher.publish(new PersonDeletedEvent(personId.getId()));
    }

    @Override
    public void removeNationalities(String deletedCountryId, String notSpecifiedId) {
        List<Person> people = findAll();
        Country notSpecified = this.countryClient.findById(CountryId.of(notSpecifiedId));

        for(Person person : people) {
            if(person.getCountryId().getId().equals(deletedCountryId)) {
                Person updatedPerson = person;
                person.setCountryId(CountryId.of(notSpecifiedId), notSpecified);

                if(person instanceof FlightAttendant) {
                    this.flightAttendantRepository.saveAndFlush((FlightAttendant) updatedPerson);
                }
                else if(person instanceof Passenger) {
                    this.passengerRepository.saveAndFlush((Passenger) updatedPerson);
                }
                else {
                    this.pilotRepository.saveAndFlush((Pilot) updatedPerson);
                }
            }
        }
    }

    @Override
    public void handleFlightArrival(Set<String> peopleIds) {
        for(String id : peopleIds) {
            Optional<Person> person = findById(PersonId.of(id));

            if(person.isEmpty()) throw new PersonNotFoundException("Person with id " + id + " does not exist.");

            Person updatedPerson = person.get();

            if(updatedPerson instanceof FlightAttendant) {
                ((FlightAttendant) updatedPerson).incrementNumFlights();
                this.flightAttendantRepository.saveAndFlush((FlightAttendant) updatedPerson);
            }
            else if(updatedPerson instanceof Pilot) {
                ((Pilot) updatedPerson).incrementYearsExperience();
                this.pilotRepository.saveAndFlush((Pilot) updatedPerson);
            }
        }
    }

    private Person toDomainObject(PersonForm personForm) {
        Country country = this.countryClient.findById(personForm.getCountryId());

        if(personForm.getNumFlights() != null) {
            return new FlightAttendant(personForm.getName(), personForm.getSurname(), personForm.getCountryId(), country, personForm.getNumFlights());
        }
        else if(personForm.getYearsExperience() != null) {
            return new Pilot(personForm.getName(), personForm.getSurname(), personForm.getCountryId(), country, personForm.getYearsExperience());
        }
        return new Passenger(personForm.getName(), personForm.getSurname(), personForm.getCountryId(), country);
    }

}
