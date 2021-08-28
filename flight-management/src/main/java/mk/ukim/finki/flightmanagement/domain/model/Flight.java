package mk.ukim.finki.flightmanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightParticipantRole;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.domain.valueobject.CountryId;
import mk.ukim.finki.flightmanagement.domain.valueobject.FlightDates;
import mk.ukim.finki.flightmanagement.domain.valueobject.Person;
import mk.ukim.finki.flightmanagement.domain.valueobject.PlaneId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.measurement.NumberOfUnits;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "flight")
public class Flight extends AbstractEntity<FlightId> {

    @AttributeOverrides({
            @AttributeOverride(name = "departureDate", column = @Column(name = "flight_departure_date")),
            @AttributeOverride(name = "arrivalDate", column = @Column(name = "flight_arrival_date"))
    })
    private FlightDates flightDates;

    @AttributeOverride(name = "value", column = @Column(name = "num_passengers"))
    private NumberOfUnits numPassengers;

    @Enumerated(value = EnumType.STRING)
    private FlightStatus flightStatus;

    @AttributeOverride(name = "id", column = @Column(name = "departure_country_id", nullable = false))
    private CountryId departureCountry;

    @AttributeOverride(name = "id", column = @Column(name = "destination_country_id", nullable = false))
    private CountryId destinationCountry;

    @AttributeOverride(name = "id", column = @Column(name = "plane_id", nullable = false))
    private PlaneId planeId;

    /**
     * Contains a set of flight participants, and each flight participant contains the ID of the actual Person.
     */
    @JoinTable(
            name = "flight_participants",
            joinColumns = @JoinColumn(
                    name = "flight_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "flight_participant_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<FlightParticipant> flightParticipants = new HashSet<>();

    protected Flight() {}

    public Flight(@NonNull FlightDates flightDates, @NonNull FlightStatus flightStatus, @NonNull CountryId departureCountry, @NonNull CountryId destinationCountry, @NonNull PlaneId planeId) {
        super(FlightId.generateRandomId(FlightId.class));
        this.flightDates = flightDates;
        this.flightStatus = flightStatus;
        this.departureCountry = departureCountry;
        this.destinationCountry = destinationCountry;
        this.planeId = planeId;

        this.numPassengers = totalNumPassengers();
    }

    public Flight(@NonNull FlightDates flightDates, @NonNull CountryId departureCountry, @NonNull CountryId destinationCountry, @NonNull PlaneId planeId) {
        super(FlightId.generateRandomId(FlightId.class));
        this.flightDates = flightDates;
        this.flightStatus = FlightStatus.SCHEDULED;
        this.departureCountry = departureCountry;
        this.destinationCountry = destinationCountry;
        this.planeId = planeId;

        this.numPassengers = totalNumPassengers();
    }

    public NumberOfUnits totalNumPassengers() {
        return NumberOfUnits.valueOf(this.flightParticipants.size());
    }

    public FlightParticipant addFlightParticipant(@NonNull Person person) {
        Objects.requireNonNull(person, "Flight participant object must not be null.");
        FlightParticipant flightParticipant;
        if(person.getNumFlights() != null) {
            flightParticipant = new FlightParticipant(FlightParticipantRole.FLIGHT_ATTENDANT, person.getPersonId());
        }
        else if(person.getYearsExperience() != null) {
            flightParticipant = new FlightParticipant(FlightParticipantRole.PILOT, person.getPersonId());
        }
        else {
            flightParticipant = new FlightParticipant(FlightParticipantRole.PASSENGER, person.getPersonId());
        }

        this.flightParticipants.add(flightParticipant);

        return flightParticipant;
    }

    public void removeFlightParticipant(@NonNull FlightParticipantId flightParticipantId) {
        Objects.requireNonNull(flightParticipantId, "Flight participant ID must not be null.");
        this.flightParticipants.removeIf(flightParticipant -> flightParticipant.getId().equals(flightParticipantId));
    }

}
