package mk.ukim.finki.flightmanagement.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flight")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightRestController {

    private final FlightService flightService;

    @GetMapping
    public List<Flight> findAll() {
        return this.flightService.findAll();
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Flight> findById(@PathVariable FlightId id) {
        return this.flightService.findById(id)
                .map(flight -> ResponseEntity.ok().body(flight))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/update-id/{id}")
    public ResponseEntity<Flight> updateById(@PathVariable FlightId id, @RequestParam @NotNull FlightStatus flightStatus) {
        return this.flightService.updateById(id, flightStatus)
                .map(flight -> ResponseEntity.ok().body(flight))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable FlightId id) {
        this.flightService.deleteById(id);
        return ResponseEntity.ok("Flight successfully deleted.");
    }

}
