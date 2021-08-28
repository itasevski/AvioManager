package mk.ukim.finki.rolemanagement.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.domain.model.*;
import mk.ukim.finki.rolemanagement.domain.model.exception.PersonNotFoundException;
import mk.ukim.finki.rolemanagement.domain.repository.FlightAttendantRepository;
import mk.ukim.finki.rolemanagement.domain.repository.PassengerRepository;
import mk.ukim.finki.rolemanagement.domain.repository.PilotRepository;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.rolemanagement.service.PersonService;
import mk.ukim.finki.rolemanagement.service.form.PersonForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonServiceImplementation implements PersonService {

    private final FlightAttendantRepository flightAttendantRepository;
    private final PassengerRepository passengerRepository;
    private final PilotRepository pilotRepository;
    private final Validator validator;

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
    public Person findById(PersonId personId) {
        Optional<? extends Person> person = this.flightAttendantRepository.existsById(personId) ? this.flightAttendantRepository.findById(personId) :
                (this.passengerRepository.existsById(personId) ? this.passengerRepository.findById(personId) :
                        this.pilotRepository.findById(personId));

        if(!person.isPresent()) throw new PersonNotFoundException("Person with id " + personId + " not found");

        return person.get();
    }

    private Person toDomainObject(PersonForm personForm) {
        if(personForm.getNumFlights() != null) {
            return new FlightAttendant(personForm.getName(), personForm.getSurname(), personForm.getCountryId(), personForm.getNumFlights());
        }
        else if(personForm.getYearsExperience() != null) {
            return new Pilot(personForm.getName(), personForm.getSurname(), personForm.getCountryId(), personForm.getYearsExperience());
        }
        return new Passenger(personForm.getName(), personForm.getSurname(), personForm.getCountryId());
    }

}
