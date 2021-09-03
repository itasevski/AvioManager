package mk.ukim.finki.flightmanagement.domain.repository;

import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.FlightId;
import mk.ukim.finki.flightmanagement.domain.valueobject.CountryId;
import mk.ukim.finki.flightmanagement.domain.valueobject.PlaneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FlightRepository interface - a JPA repository in the "flight-management" bounded context for operations with all flights.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, FlightId> {

    List<Flight> findByDepartureCountryIdOrDestinationCountryId(CountryId departureCountryId, CountryId destinationCountryId);

    List<Flight> findByPlaneId(PlaneId planeId);

}
