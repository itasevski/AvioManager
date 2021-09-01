package mk.ukim.finki.rolemanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.rolemanagement.domain.valueobject.Country;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "flight_attendant")
public class FlightAttendant extends Person {

    @AttributeOverride(name = "value", column = @Column(name = "num_flights"))
    private NumberOfUnits numFlights;

    protected FlightAttendant() {}

    public FlightAttendant(String name, String surname, @NonNull CountryId countryId, @NonNull Country country, @NonNull NumberOfUnits numFlights) {
        super(name, surname, countryId, country);
        this.numFlights = numFlights;
    }

}
