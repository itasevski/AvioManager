package mk.ukim.finki.rolemanagement.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;

@Getter
public class Country implements ValueObject {

    private final CountryName countryName;

    private Country() {
        this.countryName = null;
    }

    @JsonCreator
    public Country(@JsonProperty("countryName") CountryName countryName) {
        this.countryName = countryName;
    }

}
