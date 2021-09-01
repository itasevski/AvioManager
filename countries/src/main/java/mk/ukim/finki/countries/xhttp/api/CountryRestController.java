package mk.ukim.finki.countries.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.countries.domain.model.CountryId;
import mk.ukim.finki.countries.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable CountryId id) {
        this.countryService.deleteById(id);
        return ResponseEntity.ok("Country successfully deleted.");
    }

}
