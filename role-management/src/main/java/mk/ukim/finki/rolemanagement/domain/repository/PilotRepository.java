package mk.ukim.finki.rolemanagement.domain.repository;

import mk.ukim.finki.rolemanagement.domain.model.PersonId;
import mk.ukim.finki.rolemanagement.domain.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, PersonId> {
}
