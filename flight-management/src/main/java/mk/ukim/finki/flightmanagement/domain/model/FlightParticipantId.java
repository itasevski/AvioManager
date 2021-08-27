package mk.ukim.finki.flightmanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.EntityId;

/**
 * FlightParticipantId class - Identifier class used for FlightParticipant entities.
 */
public class FlightParticipantId extends EntityId {

    private FlightParticipantId() {
        super(FlightParticipantId.generateRandomId(FlightParticipantId.class).getId());
    }

    public FlightParticipantId(@NonNull String id) {
        super(id);
    }

    public static FlightParticipantId of(String id) {
        return new FlightParticipantId(id);
    }

}
