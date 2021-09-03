package mk.ukim.finki.sharedkernel.domain.event.plane;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;

/**
 * PlaneDeletedEvent class - an event class that extends the DomainEvent base class, used in publishing an event when a plane gets deleted.
 */
@Getter
public class PlaneDeletedEvent extends DomainEvent {

    private String deletedPlaneId;
    private String notSpecifiedId;

    public PlaneDeletedEvent() {
        super(TopicHolder.TOPIC_PLANE_DELETED);
    }

    public PlaneDeletedEvent(String deletedPlaneId, String notSpecifiedId) {
        super(TopicHolder.TOPIC_PLANE_DELETED);
        this.deletedPlaneId = deletedPlaneId;
        this.notSpecifiedId = notSpecifiedId;
    }

}
