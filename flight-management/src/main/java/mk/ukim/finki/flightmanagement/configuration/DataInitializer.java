package mk.ukim.finki.flightmanagement.configuration;

import lombok.AllArgsConstructor;
import mk.ukim.finki.flightmanagement.FlightManagementApplication;
import mk.ukim.finki.flightmanagement.domain.model.Flight;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.domain.repository.FlightParticipantRepository;
import mk.ukim.finki.flightmanagement.domain.repository.FlightRepository;
import mk.ukim.finki.flightmanagement.domain.valueobject.*;
import mk.ukim.finki.sharedkernel.domain.country.CountryName;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;
import mk.ukim.finki.sharedkernel.domain.plane.PlaneName;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * DataInitializer class - a class that inserts all the flights, along with the flight participants, into the database. (optional)
 */
@Component
@AllArgsConstructor
@Profile("dataInitializer")
public class DataInitializer {

    private final FlightRepository flightRepository;
    private final FlightParticipantRepository flightParticipantRepository;

    @PostConstruct
    public void initializeData() {
        if(flightRepository.findAll().isEmpty() && flightParticipantRepository.findAll().isEmpty()) {
            FlightDates flightDates = new FlightDates(LocalDateTime.parse("01/09/2021 14:00", FlightManagementApplication.formatter), LocalDateTime.parse("01/09/2021 16:00", FlightManagementApplication.formatter));
            Flight flight = new Flight(flightDates, CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), new Country(CountryName.Macedonia), CountryId.of("60b9c970-4f10-4067-b643-eb07e77977dd"), new Country(CountryName.Italy), PlaneId.of("eb36dfb3-3b75-4435-adcf-9de21e12455e"), new Plane(PlaneName.Airbus_A220));

            flight.addFlightParticipant(new Person(PersonId.of("7db24cdb-bd8b-4c9a-b63a-f038d23a342b"), "Passenger", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), null, null));
            flight.addFlightParticipant(new Person(PersonId.of("c02a48f9-d582-4eb0-862b-1e4dee3b1938"), "Pilot", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), null, NumberOfUnits.valueOf(10)));
            flight.addFlightParticipant(new Person(PersonId.of("291c4081-5291-4f46-ba63-6adf13def584"), "Flight Attendant", "1", CountryId.of("fe8e32c6-f124-4221-8660-08ac81319440"), NumberOfUnits.valueOf(45), null));

            this.flightRepository.save(flight);
        }
    }

}
