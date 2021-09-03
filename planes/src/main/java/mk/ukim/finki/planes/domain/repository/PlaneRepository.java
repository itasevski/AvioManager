package mk.ukim.finki.planes.domain.repository;

import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PlaneRepository interface - the JPA repository for the "planes" bounded context.
 */
@Repository
public interface PlaneRepository extends JpaRepository<Plane, PlaneId> {

    Optional<Plane> findByPlaneName(PlaneName planeName);

}
