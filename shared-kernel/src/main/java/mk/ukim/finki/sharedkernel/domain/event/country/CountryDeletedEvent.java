package mk.ukim.finki.sharedkernel.domain.event.country;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;

/**
 * CountryDeletedEvent class - an event class that extends the DomainEvent base class, used in publishing an event when a country gets deleted.
 */
@Getter
public class CountryDeletedEvent extends DomainEvent {

    private String deletedCountryId;
    private String notSpecifiedId;

    public CountryDeletedEvent() {
        super(TopicHolder.TOPIC_COUNTRY_DELETED);
    }

    public CountryDeletedEvent(String deletedCountryId, String notSpecifiedId) {
        super(TopicHolder.TOPIC_COUNTRY_DELETED);
        this.deletedCountryId = deletedCountryId;
        this.notSpecifiedId = notSpecifiedId;
    }

}
