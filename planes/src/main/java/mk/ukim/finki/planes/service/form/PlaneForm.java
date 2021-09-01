package mk.ukim.finki.planes.service.form;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;

@Data
public class PlaneForm {

    @NotNull
    private PlaneName planeName;

}
