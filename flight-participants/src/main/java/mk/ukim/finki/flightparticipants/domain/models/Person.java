package mk.ukim.finki.flightparticipants.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightparticipants.domain.valueobjects.CountryId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Person class - a superclass in the "flight-participants" bounded context that extends the AbstractEntity base superclass.
 * All other classes (flight participants) in this bounded context extend this class, thus automatically extending the
 *      AbstractEntity base superclass.
 */
@Getter
@MappedSuperclass
public class Person extends AbstractEntity<PersonId> {

    private String name;
    private String surname;

    @AttributeOverride(name = "id", column = @Column(name = "country_id"))
    private CountryId countryId;

    protected Person() {}

    public Person(String name, String surname, @NonNull CountryId countryId) {
        super(PersonId.generateRandomId(PersonId.class));
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }

}
