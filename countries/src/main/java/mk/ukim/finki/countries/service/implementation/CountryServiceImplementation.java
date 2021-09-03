package mk.ukim.finki.countries.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.countries.domain.model.CountryId;
import mk.ukim.finki.countries.domain.model.exception.CountryNotFoundException;
import mk.ukim.finki.countries.domain.repository.CountryRepository;
import mk.ukim.finki.countries.service.CountryService;
import mk.ukim.finki.countries.service.form.CountryForm;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.sharedkernel.domain.event.country.CountryDeletedEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * CountryServiceImplementation class - contains all implemented application services for the "countries" bounded context. All of the operations/services/methods
 * will be executed in a single transaction, hence the @Transactional annotation. This will ensure atomicity.
 */
@Service
@Transactional
@AllArgsConstructor
public class CountryServiceImplementation implements CountryService {

    private final CountryRepository countryRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public CountryId createCountry(CountryForm countryForm) {
        Objects.requireNonNull(countryForm, "The country form must not be null.");

        var violations = this.validator.validate(countryForm);
        if(violations.size() > 0) throw new ConstraintViolationException("The country form is not valid.", violations);

        Country country = toDomainObject(countryForm);
        this.countryRepository.saveAndFlush(country);

        return country.getId();
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(CountryId countryId) {
        return this.countryRepository.findById(countryId);
    }

    @Override
    public Optional<Country> findByCountryName(CountryName countryName) {
        return this.countryRepository.findByCountryName(countryName);
    }

    @Override
    public void deleteById(CountryId countryId) {
        if(!this.countryRepository.existsById(countryId)) throw new CountryNotFoundException("Country with id " + countryId + " does not exist.");
        this.countryRepository.deleteById(countryId);

        Optional<Country> notSpecified = findByCountryName(CountryName.NOT_SPECIFIED);

        if(notSpecified.isEmpty()) throw new CountryNotFoundException("Country with name " + CountryName.NOT_SPECIFIED.name() + " does not exist.");

        this.domainEventPublisher.publish(new CountryDeletedEvent(countryId.getId(), notSpecified.get().getId().getId()));
    }

    private Country toDomainObject(CountryForm countryForm) {
        return new Country(countryForm.getCountryName());
    }

}
