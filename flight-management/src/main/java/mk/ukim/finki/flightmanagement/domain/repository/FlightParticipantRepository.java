package mk.ukim.finki.flightmanagement.domain.repository;

import mk.ukim.finki.flightmanagement.domain.model.FlightParticipant;
import mk.ukim.finki.flightmanagement.domain.model.FlightParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightParticipantRepository extends JpaRepository<FlightParticipant, FlightParticipantId> {
}
