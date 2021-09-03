package mk.ukim.finki.flightmanagement.xhttp.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.service.FlightService;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.event.plane.PlaneDeletedEvent;
import mk.ukim.finki.sharedkernel.domain.topic.TopicHolder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * PlaneDeletionEventListener class - an event listener class that uses the @KafkaListener annotation to listen for plane deletions.
 */
@Service
@AllArgsConstructor
public class PlaneDeletionEventListener {

    private final FlightService flightService;

    @KafkaListener(topics = TopicHolder.TOPIC_PLANE_DELETED, groupId = "flightManagement")
    public void consumeCountryDeletedEvent(String json) {
        try {
            PlaneDeletedEvent planeDeletedEvent = DomainEvent.fromJson(json, PlaneDeletedEvent.class);
            this.flightService.removeDeletedPlane(planeDeletedEvent.getDeletedPlaneId(), planeDeletedEvent.getNotSpecifiedId());
        } catch (JsonProcessingException e) {

        }
    }

}
