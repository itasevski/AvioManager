package mk.ukim.finki.rolemanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.rolemanagement.RoleManagementApplication;
import mk.ukim.finki.rolemanagement.domain.valueobject.Country;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "pilot")
public class Pilot extends Person {

    @AttributeOverride(name = "value", column = @Column(name = "years_experience"))
    private NumberOfUnits yearsExperience;

    @Transient
    private LocalDateTime timeline = LocalDateTime.now();

    protected Pilot() {}

    public Pilot(String name, String surname, @NonNull CountryId countryId, @NonNull Country country, @NonNull NumberOfUnits yearsExperience) {
        super(name, surname, countryId, country);
        this.yearsExperience = yearsExperience;
    }

    /**
     * Domain service used to increment the pilot's years of experience.
     */
    public void incrementYearsExperience() {
        if(this.timeline.plusDays(365).isBefore(LocalDateTime.now())) {
            this.yearsExperience = this.yearsExperience.add(1);
            this.timeline = LocalDateTime.now();
        }
    }

}
