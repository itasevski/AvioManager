package mk.ukim.finki.countries.domain.model;

import lombok.Getter;
import mk.ukim.finki.countries.domain.model.enumerations.CountryName;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "country")
public class Country extends AbstractEntity<CountryId> {

    @Enumerated(value = EnumType.STRING)
    private CountryName countryName;

    protected Country() {}

    public Country(CountryName countryName) {
        super(CountryId.generateRandomId(CountryId.class));
        this.countryName = countryName;
    }

}
