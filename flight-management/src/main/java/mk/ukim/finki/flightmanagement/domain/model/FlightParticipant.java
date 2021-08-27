package mk.ukim.finki.flightmanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightParticipantRole;
import mk.ukim.finki.flightmanagement.domain.valueobject.PersonId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "flight_participant")
public class FlightParticipant extends AbstractEntity<FlightParticipantId> {

    @Enumerated(value = EnumType.STRING)
    private FlightParticipantRole flightParticipantRole;

    @AttributeOverride(name = "id", column = @Column(name = "person_id", nullable = false))
    private PersonId personId;

    protected FlightParticipant() {}

    public FlightParticipant(@NonNull FlightParticipantRole flightParticipantRole, @NonNull PersonId personId) {
        super(FlightParticipantId.generateRandomId(FlightParticipantId.class));
        this.flightParticipantRole = flightParticipantRole;
        this.personId = personId;
    }

}
