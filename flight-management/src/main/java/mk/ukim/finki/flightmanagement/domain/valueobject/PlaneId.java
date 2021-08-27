package mk.ukim.finki.flightmanagement.domain.valueobject;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

import javax.persistence.Embeddable;

/**
 * PlaneId class - value object used to keep track of the planes used in the flights.
 */
@Embeddable
public class PlaneId extends EntityId {

    private PlaneId() {
        super(PlaneId.generateRandomId(PlaneId.class).getId());
    }

    public PlaneId(@NonNull String id) {
        super(id);
    }

    public static PlaneId of(@NonNull String id) {
        return new PlaneId(id);
    }

}
