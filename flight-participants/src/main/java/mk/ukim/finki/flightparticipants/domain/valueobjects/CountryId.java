package mk.ukim.finki.flightparticipants.domain.valueobjects;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

import javax.persistence.Embeddable;

/**
 * CountryId class - value object used for the flight participant's nationality.
 */
@Embeddable
public class CountryId extends EntityId {

    private CountryId() {
        super(CountryId.generateRandomId(CountryId.class).getId());
    }

    public CountryId(@NonNull String id) {
        super(id);
    }

    public static CountryId of(@NonNull String id) {
        return new CountryId(id);
    }

}
