package mk.ukim.finki.flightmanagement.xhttp.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.service.FlightService;
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

    private final FlightService flightService;

    @KafkaListener(topics = TopicHolder.TOPIC_COUNTRY_DELETED, groupId = "flightManagement")
    public void consumeCountryDeletedEvent(String json) {
        try {
            CountryDeletedEvent countryDeletedEvent = DomainEvent.fromJson(json, CountryDeletedEvent.class);
            this.flightService.removeDeletedCountry(countryDeletedEvent.getDeletedCountryId(), countryDeletedEvent.getNotSpecifiedId());
        } catch (JsonProcessingException e) {

        }
    }

}
