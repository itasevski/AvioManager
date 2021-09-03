package mk.ukim.finki.planes.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.service.PlaneService;
import mk.ukim.finki.planes.service.form.PlaneForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PlaneRestController class - an Open Host Service (OHS) that contains the REST API endpoints for the "planes" bounded context.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/plane")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaneRestController {

    private final PlaneService planeService;

    @GetMapping
    public List<Plane> findAll() {
        return this.planeService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PlaneForm planeForm) {
        this.planeService.createPlane(planeForm);
        return ResponseEntity.ok("Plane successfully created.");
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Plane> findById(@PathVariable PlaneId id) {
        return this.planeService.findById(id)
                .map(plane -> ResponseEntity.ok().body(plane))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/delete-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable PlaneId id) {
        this.planeService.deleteById(id);
        return ResponseEntity.ok("Plane successfully deleted.");
    }

}
