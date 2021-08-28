package mk.ukim.finki.rolemanagement.service;

import mk.ukim.finki.rolemanagement.domain.model.*;
import mk.ukim.finki.rolemanagement.service.form.PersonForm;

import java.util.List;

public interface PersonService {

    PersonId createPerson(PersonForm personForm);

    List<Person> findAll();

    Person findById(PersonId personId);

}
