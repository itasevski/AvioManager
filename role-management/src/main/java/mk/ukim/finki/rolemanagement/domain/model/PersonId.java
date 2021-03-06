package mk.ukim.finki.rolemanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

/**
 * PersonId class - Identifier class used for person entities.
 */
public class PersonId extends EntityId {

    private PersonId() {
        super(PersonId.generateRandomId(PersonId.class).getId());
    }

    public PersonId(@NonNull String id) {
        super(id);
    }

    public static PersonId of(String id) {
        return new PersonId(id);
    }

}
