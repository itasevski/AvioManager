package mk.ukim.finki.flightmanagement.xhttp.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.service.FlightService;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.event.country.CountryDeletedEvent;
import mk.ukim.finki.sharedkernel.domain.event.person.PersonDeletedEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * PersonDeletionEventListener class - an event listener class that uses the @KafkaListener annotation to listen for person deletions.
 */
@Service
@AllArgsConstructor
public class PersonDeletionEventListener {

    private final FlightService flightService;

    @KafkaListener(topics = TopicHolder.TOPIC_PERSON_DELETED, groupId = "flightManagement")
    public void consumePersonDeletedEvent(String json) {
        try {
            PersonDeletedEvent personDeletedEvent = DomainEvent.fromJson(json, PersonDeletedEvent.class);
            this.flightService.removeFlightParticipant(personDeletedEvent.getPersonId());
        } catch (JsonProcessingException e) {

        }
    }

}
