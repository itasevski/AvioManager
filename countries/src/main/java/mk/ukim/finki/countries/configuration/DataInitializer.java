package mk.ukim.finki.countries.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.countries.domain.model.Country;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.countries.domain.repository.CountryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataInitializer class - a class that inserts all the countries from the CountryName enum into the database. (optional, but recommended)
 */
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
