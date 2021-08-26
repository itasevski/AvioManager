package mk.ukim.finki.flightparticipants.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.flightparticipants.domain.models.FlightAttendant;
import mk.ukim.finki.flightparticipants.domain.models.Passenger;
import mk.ukim.finki.flightparticipants.domain.models.Person;
import mk.ukim.finki.flightparticipants.domain.models.Pilot;
import mk.ukim.finki.flightparticipants.domain.repository.FlightAttendantRepository;
import mk.ukim.finki.flightparticipants.domain.repository.PassengerRepository;
import mk.ukim.finki.flightparticipants.domain.repository.PilotRepository;
import mk.ukim.finki.flightparticipants.domain.valueobjects.CountryId;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final FlightAttendantRepository flightAttendantRepository;
    private final PassengerRepository passengerRepository;
    private final PilotRepository pilotRepository;

    @PostConstruct
    public void initializeData() {
        Pilot pilot1 = new Pilot("Pilot", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), NumberOfUnits.valueOf(10));
        Pilot pilot2 = new Pilot("Pilot", "2", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), NumberOfUnits.valueOf(15));

        if(this.pilotRepository.findAll().isEmpty()) {
            this.pilotRepository.saveAll(Arrays.asList(pilot1, pilot2));
        }

        FlightAttendant flightAttendant1 = new FlightAttendant("FlightAttendant", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), NumberOfUnits.valueOf(45));
        FlightAttendant flightAttendant2 = new FlightAttendant("FlightAttendant", "2", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), NumberOfUnits.valueOf(50));
        FlightAttendant flightAttendant3 = new FlightAttendant("FlightAttendant", "3", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), NumberOfUnits.valueOf(55));

        if(this.flightAttendantRepository.findAll().isEmpty()) {
            this.flightAttendantRepository.saveAll(Arrays.asList(flightAttendant1, flightAttendant2, flightAttendant3));
        }

        Passenger passenger1 = new Passenger("Passenger", "1", CountryId.of("b27939e3-b4e3-4810-86ec-53c60346ccb5"));
        Passenger passenger2 = new Passenger("Passenger", "2", CountryId.of("1c2fc998-4738-4098-bb88-112173ab95fc"));
        Passenger passenger3 = new Passenger("Passenger", "3", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"));
        Passenger passenger4 = new Passenger("Passenger", "4", CountryId.of("60b9c970-4f10-4067-b643-eb07e77977dd"));

        if(this.passengerRepository.findAll().isEmpty()) {
            this.passengerRepository.saveAll(Arrays.asList(passenger1, passenger2, passenger3, passenger4));
        }

    }

}
