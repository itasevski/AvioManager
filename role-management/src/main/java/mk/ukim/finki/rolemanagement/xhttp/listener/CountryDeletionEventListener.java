package mk.ukim.finki.rolemanagement.xhttp.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.service.PersonService;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.event.country.CountryDeletedEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * CountryDeletionEventListener class - an event listener class that uses the @KafkaListener annotation to listen for country deletions.
 */
@Service
@AllArgsConstructor
public class CountryDeletionEventListener {

    private final PersonService personService;

    @KafkaListener(topics = TopicHolder.TOPIC_COUNTRY_DELETED, groupId = "roleManagement")
    public void consumeCountryDeletedEvent(String json) {
        try {
            CountryDeletedEvent countryDeletedEvent = DomainEvent.fromJson(json, CountryDeletedEvent.class);
            this.personService.removeNationalities(countryDeletedEvent.getDeletedCountryId(), countryDeletedEvent.getNotSpecifiedId());
        } catch (JsonProcessingException e) {

        }
    }

}
