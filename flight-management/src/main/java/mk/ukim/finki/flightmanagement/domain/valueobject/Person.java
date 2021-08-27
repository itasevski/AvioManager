package mk.ukim.finki.flightmanagement.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;

/**
 * Person class - an immutable value object used as a DTO used for FlightParticipant entities, correlated to the Person class
 * in the role-management bounded context.
 */
@Getter
public class Person implements ValueObject {

    private final PersonId personId;
    private final String name;
    private final String surname;
    private final CountryId countryId;
    private final NumberOfUnits numFlights;
    private final NumberOfUnits yearsExperience;

    private Person() {
        this.personId = PersonId.generateRandomId(PersonId.class);
        this.name = "";
        this.surname = "";
        this.countryId = CountryId.generateRandomId(CountryId.class);
        this.numFlights = NumberOfUnits.valueOf(0);
        this.yearsExperience = NumberOfUnits.valueOf(0);
    }

    @JsonCreator
    public Person(@JsonProperty("id") PersonId personId,
                  @JsonProperty("name") String name,
                  @JsonProperty("surname") String surname,
                  @JsonProperty("countryId") CountryId countryId,
                  @JsonProperty("numFlights") NumberOfUnits numFlights,
                  @JsonProperty("yearsExperience") NumberOfUnits yearsExperience) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
        this.numFlights = numFlights;
        this.yearsExperience = yearsExperience;
    }

}
