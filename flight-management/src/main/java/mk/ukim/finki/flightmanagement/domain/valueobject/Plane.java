package mk.ukim.finki.flightmanagement.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;

/**
 * Plane class - an immutable value object that represents data from the Plane aggregate (planes BC). It is needed for planes used in the flights.
 */
@Getter
public class Plane implements ValueObject {

    private final PlaneName planeName;

    private Plane() {
        this.planeName = null;
    }

    /**
     * A public constructor, annotated with the @JsonCreator annotation that deserializes response JSON fields into actual fields annotated with the @JsonProperty
     * annotation, that contains the expected JSON field name that will be deserialized into the actual class/value object attribute/property.
     * @param planeName - the attribute/property that gets injected with the value from the "planeName" JSON field.
     */
    @JsonCreator
    public Plane(@JsonProperty("planeName") PlaneName planeName) {
        this.planeName = planeName;
    }

}
