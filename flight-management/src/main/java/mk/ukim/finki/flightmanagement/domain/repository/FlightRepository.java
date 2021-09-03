package mk.ukim.finki.flightmanagement.domain.repository;

import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FlightRepository interface - a JPA repository in the "flight-management" bounded context for operations with all flights.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, FlightId> {
}
