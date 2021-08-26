package mk.ukim.finki.flightparticipants.domain.repository;

import mk.ukim.finki.flightparticipants.domain.models.PersonId;
import mk.ukim.finki.flightparticipants.domain.models.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, PersonId> {
}
