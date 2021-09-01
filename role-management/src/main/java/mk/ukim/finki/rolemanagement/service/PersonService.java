package mk.ukim.finki.rolemanagement.service;

import mk.ukim.finki.rolemanagement.domain.model.*;
import mk.ukim.finki.rolemanagement.service.form.PersonForm;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonId createPerson(PersonForm personForm);

    List<Person> findAll();

    Optional<Person> findById(PersonId personId);

    void deleteById(PersonId personId);

}
