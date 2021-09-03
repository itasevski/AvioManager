package mk.ukim.finki.planes.domain.repository;

import mk.ukim.finki.planes.domain.model.Plane;
import mk.ukim.finki.planes.domain.model.PlaneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PlaneRepository interface - the JPA repository for the "planes" bounded context.
 */
@Repository
public interface PlaneRepository extends JpaRepository<Plane, PlaneId> {
}
