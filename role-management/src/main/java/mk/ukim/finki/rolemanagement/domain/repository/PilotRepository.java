package mk.ukim.finki.rolemanagement.domain.repository;

import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import mk.ukim.finki.rolemanagement.domain.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PilotRepository interface - a JPA repository in the "role-management" bounded context for operations with all pilots.
 */
@Repository
public interface PilotRepository extends JpaRepository<Pilot, PersonId> {
}
