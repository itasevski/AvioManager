package mk.ukim.finki.countries.service;

import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.countries.domain.model.CountryId;
import mk.ukim.finki.countries.service.form.CountryForm;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    CountryId createCountry(CountryForm countryForm);

    List<Country> findAll();

    Optional<Country> findById(CountryId countryId);

    Optional<Country> findByCountryName(CountryName countryName);

    void deleteById(CountryId countryId);

}
