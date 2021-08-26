package mk.ukim.finki.flightparticipants.domain.repository;

import mk.ukim.finki.flightparticipants.domain.models.FlightAttendant;
import mk.ukim.finki.flightparticipants.domain.models.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightAttendantRepository extends JpaRepository<FlightAttendant, PersonId> {
}
