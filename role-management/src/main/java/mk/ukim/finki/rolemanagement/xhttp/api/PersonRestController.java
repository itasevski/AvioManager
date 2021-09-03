package mk.ukim.finki.rolemanagement.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.domain.model.Person;
import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import mk.ukim.finki.rolemanagement.service.PersonService;
import mk.ukim.finki.rolemanagement.service.form.PersonForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonRestController class - an Open Host Service (OHS) that contains the REST API endpoints for the "role-management" bounded context.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonRestController {

    private final PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return this.personService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PersonForm personForm) {
        this.personService.createPerson(personForm);
        return ResponseEntity.ok("Person successfully created.");
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Person> findById(@PathVariable PersonId id) {
        return this.personService.findById(id)
                .map(person -> ResponseEntity.ok().body(person))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable PersonId id) {
        this.personService.deleteById(id);
        return ResponseEntity.ok("Person successfully deleted.");
    }

}
