package mk.ukim.finki.sharedkernel.domain.event.person;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;

/**
 * PersonDeletedEvent class - an event class that extends the DomainEvent base class, used in publishing an event when a person gets deleted.
 */
@Getter
public class PersonDeletedEvent extends DomainEvent {

    private String personId;

    public PersonDeletedEvent() {
        super(TopicHolder.TOPIC_PERSON_DELETED);
    }

    public PersonDeletedEvent(String personId) {
        super(TopicHolder.TOPIC_PERSON_DELETED);
        this.personId = personId;
    }

}
