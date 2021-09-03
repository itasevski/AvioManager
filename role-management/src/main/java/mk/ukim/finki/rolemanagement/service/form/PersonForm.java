package mk.ukim.finki.rolemanagement.service.form;

import lombok.Data;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;

import javax.validation.constraints.NotNull;

/**
 * PersonForm class - class used to cast request bodies into PersonForm instances, later used to create actual Person entities.
 */
@Data
public class PersonForm {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private CountryId countryId;

    private NumberOfUnits numFlights;

    private NumberOfUnits yearsExperience;

}
