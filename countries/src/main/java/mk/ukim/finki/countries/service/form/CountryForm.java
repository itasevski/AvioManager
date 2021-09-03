package mk.ukim.finki.countries.service.form;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;

/**
 * CountryForm class - class used to cast request bodies into CountryForm instances, later used to create actual Country entities.
 */
@Data
public class CountryForm {

    @NotNull
    private CountryName countryName;

}
