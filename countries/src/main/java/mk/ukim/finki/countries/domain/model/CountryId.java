package mk.ukim.finki.countries.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

/**
 * CountryId class - Identifier class used for Country entities.
 */
public class CountryId extends EntityId {

    private CountryId() {
        super(CountryId.generateRandomId(CountryId.class).getId());
    }

    public CountryId(@NonNull String id) {
        super(id);
    }

    public CountryId of(String id) {
        return new CountryId(id);
    }

}
