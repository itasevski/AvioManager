package mk.ukim.finki.countries.domain.repository;

import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.countries.domain.model.CountryId;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CountryRepository interface - the JPA repository for the "countries" bounded context.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, CountryId> {

    Optional<Country> findByCountryName(CountryName countryName);

}
