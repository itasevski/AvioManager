package mk.ukim.finki.sharedkernel.domain.event.flight;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;

import java.util.Set;

/**
 * FlightArrivedEvent class - an event class that extends the DomainEvent base class, used in publishing an event when a flight arrives to its destination.
 */
@Getter
public class FlightArrivedEvent extends DomainEvent {

    private Set<String> flightParticipantIds;

    public FlightArrivedEvent() {
        super(TopicHolder.TOPIC_FLIGHT_ARRIVED);
    }

    public FlightArrivedEvent(Set<String> flightParticipantIds) {
        super(TopicHolder.TOPIC_FLIGHT_ARRIVED);
        this.flightParticipantIds = flightParticipantIds;
    }

}
