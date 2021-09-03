package mk.ukim.finki.countries.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.countries.domain.model.CountryId;
import mk.ukim.finki.countries.service.CountryService;
import mk.ukim.finki.countries.service.form.CountryForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CountryRestController class - an Open Host Service (OHS) that contains the REST API endpoints for the "countries" bounded context.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/country")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return this.countryService.findAll();
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Country> findById(@PathVariable CountryId id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CountryForm countryForm) {
        this.countryService.createCountry(countryForm);
        return ResponseEntity.ok("Country successfully created.");
    }

    @GetMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable CountryId id) {
        this.countryService.deleteById(id);
        return ResponseEntity.ok("Country successfully deleted.");
    }

}
