package mk.ukim.finki.planes.service.form;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.planes.domain.model.enumeration.PlaneName;

@Data
public class PlaneForm {

    @NotNull
    private PlaneName planeName;

}
