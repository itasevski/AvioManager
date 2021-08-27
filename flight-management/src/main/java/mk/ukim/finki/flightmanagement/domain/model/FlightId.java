package mk.ukim.finki.flightmanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

/**
 * FlightId class - Identifier class used for Flight entities.
 */
public class FlightId extends EntityId {

    private FlightId() {
        super(FlightId.generateRandomId(FlightId.class).getId());
    }

    public FlightId(@NonNull String id) {
        super(id);
    }

    public static FlightId of(String id) {
        return new FlightId(id);
    }

}
