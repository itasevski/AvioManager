package mk.ukim.finki.flightmanagement.domain.valueobject;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

import javax.persistence.Embeddable;

/**
 * PersonId class - value object used for flight participant entities.
 */
@Embeddable
public class PersonId extends EntityId {

    private PersonId() {
        super(PersonId.generateRandomId(PersonId.class).getId());
    }

    public PersonId(@NonNull String id) {
        super(id);
    }

    public static PersonId of(@NonNull String id) {
        return new PersonId(id);
    }

}
