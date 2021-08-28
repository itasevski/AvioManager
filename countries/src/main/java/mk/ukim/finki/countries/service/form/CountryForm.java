package mk.ukim.finki.countries.service.form;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.countries.domain.model.enumeration.CountryName;

@Data
public class CountryForm {

    @NotNull
    private CountryName countryName;

}
