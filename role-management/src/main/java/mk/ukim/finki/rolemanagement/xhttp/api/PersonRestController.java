package mk.ukim.finki.rolemanagement.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.domain.model.Person;
import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import mk.ukim.finki.rolemanagement.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
