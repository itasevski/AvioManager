package mk.ukim.finki.countries.service;

import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.countries.domain.model.CountryId;
import mk.ukim.finki.countries.service.form.CountryForm;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    CountryId createCountry(CountryForm countryForm);

    List<Country> findAll();

    Optional<Country> findById(CountryId countryId);

    void deleteById(CountryId countryId);

}
