package mk.ukim.finki.flightmanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightParticipantRole;
import mk.ukim.finki.flightmanagement.domain.valueobject.Person;
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
    private Person person;

    protected FlightParticipant() {}

    public FlightParticipant(@NonNull FlightParticipantRole flightParticipantRole, @NonNull PersonId personId, @NonNull Person person) {
        super(FlightParticipantId.generateRandomId(FlightParticipantId.class));
        this.flightParticipantRole = flightParticipantRole;
        this.personId = personId;

        setPerson(person);
    }

    /**
     * Domain service used to set the person object related to the flight participant.
     * @param person - a person value object representing the actual role-management aggregate.
     */
    private void setPerson(Person person) {
        this.person = person;
    }

}
