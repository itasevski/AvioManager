package mk.ukim.finki.rolemanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.rolemanagement.domain.valueobject.Country;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Person class - a superclass in the "role-management" bounded context that extends the AbstractEntity base superclass.
 * All other classes in this bounded context extend this class, thus automatically extending the AbstractEntity base superclass.
 */
@Getter
@MappedSuperclass
public abstract class Person extends AbstractEntity<PersonId> {

    private String name;
    private String surname;

    @AttributeOverride(name = "id", column = @Column(name = "country_id", nullable = false))
    private CountryId countryId;
    private Country country;

    protected Person() {}

    public Person(String name, String surname, @NonNull CountryId countryId, @NonNull Country country) {
        super(PersonId.generateRandomId(PersonId.class));
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;

        setCountry(country);
    }

    /**
     * Domain service used to set the Person's nationality.
     * @param countryId - the country's ID.
     * @param country - a country value object representing the actual country aggregate.
     */
    public void setCountryId(@NonNull CountryId countryId, @NonNull Country country) {
        this.countryId = countryId;
        setCountry(country);
    }

    /**
     * Domain service that is called in the previous domain service. It is used to set the country object.
     * @param country - a country value object representing the actual country aggregate.
     */
    private void setCountry(@NonNull Country country) {
        this.country = country;
    }

}
