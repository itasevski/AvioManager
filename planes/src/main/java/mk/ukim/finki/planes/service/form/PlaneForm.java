package mk.ukim.finki.planes.service.form;

import lombok.Data;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;

import javax.validation.constraints.NotNull;

/**
 * PlaneForm class - class used to cast request bodies into PlaneForm instances, later used to create actual Plane entities.
 */
@Data
public class PlaneForm {

    @NotNull
    private PlaneName planeName;

    @NotNull
    private NumberOfUnits numSeats;

}
