package mk.ukim.finki.flightmanagement.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;

@Getter
public class Plane implements ValueObject {

    private final PlaneName planeName;

    private Plane() {
        this.planeName = null;
    }

    @JsonCreator
    public Plane(@JsonProperty("planeName") PlaneName planeName) {
        this.planeName = planeName;
    }

}
