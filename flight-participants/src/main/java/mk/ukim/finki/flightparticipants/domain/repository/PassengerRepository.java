package mk.ukim.finki.flightparticipants.domain.repository;

import mk.ukim.finki.flightparticipants.domain.models.Passenger;
import mk.ukim.finki.flightparticipants.domain.models.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, PersonId> {
}
