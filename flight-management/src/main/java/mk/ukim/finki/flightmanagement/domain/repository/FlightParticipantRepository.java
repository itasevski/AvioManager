package mk.ukim.finki.flightmanagement.domain.repository;

import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FlightParticipantRepository interface - a JPA repository in the "flight-management" bounded context for operations with flight participants from all flights.
 */
@Repository
public interface FlightParticipantRepository extends JpaRepository<FlightParticipant, FlightParticipantId> {
}
