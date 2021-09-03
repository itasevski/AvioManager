package mk.ukim.finki.rolemanagement.xhttp.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.service.PersonService;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.event.flight.FlightArrivedEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * ArrivedFlightEventListener class - an event listener class that uses the @KafkaListener annotation to listen for flight arrival events.
 */
@Service
@AllArgsConstructor
public class ArrivedFlightEventListener {

    private final PersonService personService;

    @KafkaListener(topics = TopicHolder.TOPIC_FLIGHT_ARRIVED, groupId = "roleManagement")
    public void consumeFlightArrivedEvent(String json) {
        try {
            FlightArrivedEvent flightArrivedEvent = DomainEvent.fromJson(json, FlightArrivedEvent.class);
            this.personService.handleFlightArrival(flightArrivedEvent.getFlightParticipantIds());
        } catch (JsonProcessingException e) {

        }
    }

}
