package mk.ukim.finki.planes.xhttp.api;

import lombok.AllArgsConstructor;
import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.planes.service.PlaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
