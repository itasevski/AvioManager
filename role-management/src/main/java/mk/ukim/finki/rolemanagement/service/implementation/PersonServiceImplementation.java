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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
