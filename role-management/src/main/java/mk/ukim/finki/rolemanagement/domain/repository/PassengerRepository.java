package mk.ukim.finki.rolemanagement.domain.repository;

import mk.ukim.finki.rolemanagement.domain.model.Passenger;
import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PassengerRepository interface - a JPA repository in the "role-management" bounded context for operations with all passengers.
 */
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, PersonId> {
}
