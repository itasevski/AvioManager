package mk.ukim.finki.rolemanagement.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.domain.model.Person;
import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import mk.ukim.finki.rolemanagement.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/person")
public class PersonRestController {

    private final PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return this.personService.findAll();
    }

    @GetMapping("/find-id/{id}")
    public Person findById(@PathVariable PersonId id) {
        return this.personService.findById(id);
    }

}
