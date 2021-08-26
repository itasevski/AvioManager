package mk.ukim.finki.flightparticipants.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightparticipants.domain.valueobjects.CountryId;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "pilot")
public class Pilot extends Person {

    @AttributeOverride(name = "value", column = @Column(name = "years_experience"))
    private NumberOfUnits yearsExperience;

    protected Pilot() {}

    public Pilot(String name, String surname, @NonNull CountryId countryId, @NonNull NumberOfUnits yearsExperience) {
        super(name, surname, countryId);
        this.yearsExperience = yearsExperience;
    }

}
