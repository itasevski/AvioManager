package mk.ukim.finki.sharedkernel.domain.measurement;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * NumberOfUnits class - an immutable measurement value object for Integer fields.
 * The Embeddable annotation ensures its database persistence.
 */
@Getter
@Embeddable
public class NumberOfUnits implements ValueObject {

    private final int value;

    protected NumberOfUnits() {
        this.value = 0;
    }

    public NumberOfUnits(int value) {
        this.value = value;
    }

    public static NumberOfUnits valueOf(int value) {
        return new NumberOfUnits(value);
    }

    public NumberOfUnits add(int value) {
        return new NumberOfUnits(this.value + value);
    }

    public NumberOfUnits subtract(int value) {
        return new NumberOfUnits(this.value - value);
    }

    public NumberOfUnits multiply(int value) {
        return new NumberOfUnits(this.value * value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        NumberOfUnits units = (NumberOfUnits) obj;
        return this.value == units.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

}
