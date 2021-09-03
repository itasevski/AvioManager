package mk.ukim.finki.rolemanagement.infra;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * DomainEventPublisherImplementation class - a class that implements the DomainEventPublisher interface and uses the KafkaTemplate class
 * to send events with specified topics and payloads.
 */
@Service
@AllArgsConstructor
public class DomainEventPublisherImplementation implements DomainEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(DomainEvent domainEvent) {
        this.kafkaTemplate.send(domainEvent.getTopic(), domainEvent.toJson());
    }

}
