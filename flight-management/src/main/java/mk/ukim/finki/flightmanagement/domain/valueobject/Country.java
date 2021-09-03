package mk.ukim.finki.flightmanagement.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;

/**
 * Country class - an immutable value object that represents data from the Country aggregate (countries BC). It is used for flights' departure and destination countries.
 */
@Getter
public class Country implements ValueObject {

    private final CountryName countryName;

    private Country() {
        this.countryName = null;
    }

    /**
     * A public constructor, annotated with the @JsonCreator annotation that deserializes response JSON fields into actual fields annotated with the @JsonProperty
     * annotation, that contains the expected JSON field name that will be deserialized into the actual class/value object attribute/property.
     * @param countryName - the attribute/property that gets injected with the value from the "countryName" JSON field.
     */
    @JsonCreator
    public Country(@JsonProperty("countryName") CountryName countryName) {
        this.countryName = countryName;
    }

}
