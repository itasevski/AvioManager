package mk.ukim.finki.planes.service.form;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;

/**
 * PlaneForm class - class used to cast request bodies into PlaneForm instances, later used to create actual Plane entities.
 */
@Data
public class PlaneForm {

    @NotNull
    private PlaneName planeName;

}
