package mk.ukim.finki.planes.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import mk.ukim.finki.planes.domain.repository.PlaneRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataInitializer class - a class that inserts all the planes from the PlaneName enum into the database. (optional, but recommended)
 */
@Component
@AllArgsConstructor
public class DataInitializer {

    private final PlaneRepository planeRepository;

    @PostConstruct
    public void initializeData() {
        if(this.planeRepository.findAll().isEmpty()) {
            int numSeats = 100;

            for(PlaneName planeName : PlaneName.values()) {
                if(numSeats > 300) {
                    numSeats = 100;
                }

                numSeats += 75;

                if(planeName == PlaneName.NOT_SPECIFIED) {
                    numSeats = 0;
                }

                this.planeRepository.save(new Plane(planeName, NumberOfUnits.valueOf(numSeats)));
            }
        }
    }

}
