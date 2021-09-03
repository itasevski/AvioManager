package mk.ukim.finki.rolemanagement.domain.repository;

import mk.ukim.finki.rolemanagement.domain.model.FlightAttendant;
import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FlightAttendantRepository interface - a JPA repository in the "role-management" bounded context for operations with all flight attendants.
 */
@Repository
public interface FlightAttendantRepository extends JpaRepository<FlightAttendant, PersonId> {
}
