package mk.ukim.finki.planes.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import mk.ukim.finki.planes.domain.repository.PlaneRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final PlaneRepository planeRepository;

    @PostConstruct
    public void initializeData() {
        if(this.planeRepository.findAll().isEmpty()) {
            for(PlaneName planeName : PlaneName.values()) {
                this.planeRepository.save(new Plane(planeName));
            }
        }
    }

}
