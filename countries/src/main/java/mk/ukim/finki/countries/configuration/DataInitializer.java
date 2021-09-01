package mk.ukim.finki.countries.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.countries.domain.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final CountryRepository countryRepository;

    @PostConstruct
    public void initializeData() {
        if(this.countryRepository.findAll().isEmpty()) {
            for(CountryName countryName : CountryName.values()) {
                this.countryRepository.save(new Country(countryName));
            }
        }
    }

}
