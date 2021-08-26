package mk.ukim.finki.flightparticipants.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightparticipants.domain.valueobjects.CountryId;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "passenger")
public class Passenger extends Person {

    protected Passenger() {}

    public Passenger(String name, String surname, @NonNull CountryId countryId) {
        super(name, surname, countryId);
    }

}
