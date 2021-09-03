package mk.ukim.finki.rolemanagement.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rolemanagement.domain.model.FlightAttendant;
import mk.ukim.finki.rolemanagement.domain.model.Passenger;
import mk.ukim.finki.rolemanagement.domain.model.Pilot;
import mk.ukim.finki.rolemanagement.domain.repository.FlightAttendantRepository;
import mk.ukim.finki.rolemanagement.domain.repository.PassengerRepository;
import mk.ukim.finki.rolemanagement.domain.repository.PilotRepository;
import mk.ukim.finki.rolemanagement.domain.valueobject.Country;
import mk.ukim.finki.rolemanagement.domain.valueobject.CountryId;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * DataInitializer class - a class that inserts all the people related to flights into the database. (optional)
 */
@Component
@AllArgsConstructor
@Profile("dataInitializer")
public class DataInitializer {

    private final FlightAttendantRepository flightAttendantRepository;
    private final PassengerRepository passengerRepository;
    private final PilotRepository pilotRepository;

    @PostConstruct
    public void initializeData() {
        if(this.pilotRepository.findAll().isEmpty() && this.flightAttendantRepository.findAll().isEmpty() && this.passengerRepository.findAll().isEmpty()) {
            Pilot pilot1 = new Pilot("Pilot", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia), NumberOfUnits.valueOf(10));
            Pilot pilot2 = new Pilot("Pilot", "2", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia), NumberOfUnits.valueOf(15));

            FlightAttendant flightAttendant1 = new FlightAttendant("FlightAttendant", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia), NumberOfUnits.valueOf(45));
            FlightAttendant flightAttendant2 = new FlightAttendant("FlightAttendant", "2", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia), NumberOfUnits.valueOf(50));
            FlightAttendant flightAttendant3 = new FlightAttendant("FlightAttendant", "3", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia), NumberOfUnits.valueOf(55));

            Passenger passenger1 = new Passenger("Passenger", "1", CountryId.of("b27939e3-b4e3-4810-86ec-53c60346ccb5"), new Country(CountryName.France));
            Passenger passenger2 = new Passenger("Passenger", "2", CountryId.of("1c2fc998-4738-4098-bb88-112173ab95fc"), new Country(CountryName.Greece));
            Passenger passenger3 = new Passenger("Passenger", "3", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia));
            Passenger passenger4 = new Passenger("Passenger", "4", CountryId.of("60b9c970-4f10-4067-b643-eb07e77977dd"), new Country(CountryName.Italy));

            this.pilotRepository.saveAll(Arrays.asList(pilot1, pilot2));
            this.flightAttendantRepository.saveAll(Arrays.asList(flightAttendant1, flightAttendant2, flightAttendant3));
            this.passengerRepository.saveAll(Arrays.asList(passenger1, passenger2, passenger3, passenger4));
        }
    }

}
