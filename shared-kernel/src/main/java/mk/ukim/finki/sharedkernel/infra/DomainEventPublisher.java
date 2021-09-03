package mk.ukim.finki.sharedkernel.infra;

import mk.ukim.finki.sharedkernel.domain.event.DomainEvent;

/**
 * DomainEventPublisher interface - a base interface implemented in every bounded context where an event needs to be published/sent to groups of consumers.
 */
public interface DomainEventPublisher {

    void publish(DomainEvent domainEvent);

}
