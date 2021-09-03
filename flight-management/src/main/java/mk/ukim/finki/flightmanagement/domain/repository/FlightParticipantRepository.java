package mk.ukim.finki.flightmanagement.domain.repository;

import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import mk.ukim.finki.flightmanagement.domain.valueobject.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FlightParticipantRepository interface - a JPA repository in the "flight-management" bounded context for operations with flight participants from all flights.
 */
@Repository
public interface FlightParticipantRepository extends JpaRepository<FlightParticipant, FlightParticipantId> {
}
