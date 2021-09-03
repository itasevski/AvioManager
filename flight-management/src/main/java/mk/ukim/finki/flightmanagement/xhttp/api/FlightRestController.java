package mk.ukim.finki.flightmanagement.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.domain.valueobject.PersonId;
import mk.ukim.finki.flightmanagement.service.FlightService;
import mk.ukim.finki.flightmanagement.service.form.FlightForm;
import mk.ukim.finki.flightmanagement.service.form.FlightParticipantForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * FlightRestController class - an Open Host Service (OHS) that contains the REST API endpoints for the "flight-management" bounded context.
 */
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

    @GetMapping("/find-fp/{id}")
    public Set<FlightParticipant> getFlightParticipants(@PathVariable FlightId id) {
        return this.flightService.getFlightParticipantsByFlightId(id);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Flight> findById(@PathVariable FlightId id) {
        return this.flightService.findById(id)
                .map(flight -> ResponseEntity.ok().body(flight))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FlightForm flightForm) {
        this.flightService.createFlight(flightForm);
        return ResponseEntity.ok("Flight successfully created.");
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

    @PostMapping("/add-fp/{id}")
    public ResponseEntity<String> addFlightParticipant(@PathVariable FlightId id, @RequestBody FlightParticipantForm flightParticipantForm) {
        this.flightService.addFlightParticipant(id, flightParticipantForm);
        return ResponseEntity.ok("Flight participant successfully added to flight.");
    }

    @GetMapping("/delete-fp/{id}")
    public ResponseEntity<String> deleteFlightParticipant(@PathVariable FlightId id, @RequestParam @NotNull FlightParticipantId flightParticipantId) {
        this.flightService.removeFlightParticipant(id, flightParticipantId);
        return ResponseEntity.ok("Flight participant successfully removed from flight.");
    }

}
