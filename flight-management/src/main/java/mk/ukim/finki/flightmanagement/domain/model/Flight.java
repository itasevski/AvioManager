package mk.ukim.finki.flightmanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightParticipantRole;
import mk.ukim.finki.flightmanagement.domain.model.enumeration.FlightStatus;
import mk.ukim.finki.flightmanagement.domain.valueobject.*;
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

    @AttributeOverride(name = "value", column = @Column(name = "num_participants"))
    private NumberOfUnits numParticipants;

    @Enumerated(value = EnumType.STRING)
    private FlightStatus flightStatus;

    @AttributeOverride(name = "id", column = @Column(name = "departure_country_id", nullable = false))
    private CountryId departureCountryId;
    private Country departureCountry;

    @AttributeOverride(name = "id", column = @Column(name = "destination_country_id", nullable = false))
    private CountryId destinationCountryId;
    private Country destinationCountry;

    @AttributeOverride(name = "id", column = @Column(name = "plane_id", nullable = false))
    private PlaneId planeId;
    private Plane plane;

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

    public Flight(@NonNull FlightDates flightDates, @NonNull FlightStatus flightStatus, @NonNull CountryId departureCountryId, @NonNull Country departureCountry, @NonNull CountryId destinationCountryId, @NonNull Country destinationCountry, @NonNull PlaneId planeId, @NonNull Plane plane) {
        super(FlightId.generateRandomId(FlightId.class));
        this.flightDates = flightDates;
        this.flightStatus = flightStatus;
        this.departureCountryId = departureCountryId;
        this.destinationCountryId = destinationCountryId;
        this.planeId = planeId;

        this.numParticipants = totalNumParticipants();
        setDepartureCountry(departureCountry);
        setDestinationCountry(destinationCountry);
        setPlane(plane);
    }

    public Flight(@NonNull FlightDates flightDates, @NonNull CountryId departureCountryId, @NonNull Country departureCountry, @NonNull CountryId destinationCountryId, @NonNull Country destinationCountry, @NonNull PlaneId planeId, @NonNull Plane plane) {
        super(FlightId.generateRandomId(FlightId.class));
        this.flightDates = flightDates;
        this.flightStatus = FlightStatus.SCHEDULED;
        this.departureCountryId = departureCountryId;
        this.destinationCountryId = destinationCountryId;
        this.planeId = planeId;

        this.numParticipants = totalNumParticipants();
        setDepartureCountry(departureCountry);
        setDestinationCountry(destinationCountry);
        setPlane(plane);
    }

    /**
     * Domain service used to add a flight participant to the flight.
     * @param person - the value object injected into the newly created flight participant, containing data of the actual person from the "role-management" bounded context.
     */
    public void addFlightParticipant(@NonNull Person person) {
        Objects.requireNonNull(person, "Flight participant object must not be null.");
        FlightParticipant flightParticipant;
        if(person.getNumFlights() != null) {
            flightParticipant = new FlightParticipant(FlightParticipantRole.FLIGHT_ATTENDANT, person.getPersonId(), person);
        }
        else if(person.getYearsExperience() != null) {
            flightParticipant = new FlightParticipant(FlightParticipantRole.PILOT, person.getPersonId(), person);
        }
        else {
            flightParticipant = new FlightParticipant(FlightParticipantRole.PASSENGER, person.getPersonId(), person);
        }

        this.flightParticipants.add(flightParticipant);
        this.numParticipants = this.numParticipants.add(1);
    }

    /**
     * Domain service used to remove a flight participant from the flight.
     * @param flightParticipantId - the ID of the flight participant to be removed from the flight.
     */
    public void removeFlightParticipant(@NonNull FlightParticipantId flightParticipantId) {
        Objects.requireNonNull(flightParticipantId, "Flight participant ID must not be null.");
        this.flightParticipants.removeIf(flightParticipant -> flightParticipant.getId().equals(flightParticipantId));
        this.numParticipants = this.numParticipants.subtract(1);
    }

    /**
     * Domain service used to set the flight's status.
     * @param flightStatus - the new flight status.
     */
    public void setFlightStatus(@NonNull FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    /**
     * Domain service used to return the total number of participants in the flight.
     * @return the total number of participants in the flight.
     */
    private NumberOfUnits totalNumParticipants() {
        return NumberOfUnits.valueOf(this.flightParticipants.size());
    }

    /**
     * Domain service used to set the flight's departure country.
     * @param departureCountryId - the departure country's ID.
     * @param country - a country value object representing the actual country aggregate.
     */
    public void setDepartureCountryId(@NonNull CountryId departureCountryId, @NonNull Country country) {
        this.departureCountryId = departureCountryId;
        setDepartureCountry(country);
    }

    /**
     * Domain service that is called in the previous domain service. It is used to set the departure country object.
     * @param departureCountry - a country value object representing the actual country aggregate.
     */
    private void setDepartureCountry(@NonNull Country departureCountry) {
        this.departureCountry = departureCountry;
    }

    /**
     * Domain service used to set the flight's destination country.
     * @param destinationCountryId - the destination country's ID.
     * @param country - a country value object representing the actual country aggregate.
     */
    public void setDestinationCountryId(@NonNull CountryId destinationCountryId, @NonNull Country country) {
        this.destinationCountryId = destinationCountryId;
        setDestinationCountry(country);
    }

    /**
     * Domain service that is called in the previous domain service. It is used to set the destination country object.
     * @param destinationCountry - a country value object representing the actual country aggregate.
     */
    private void setDestinationCountry(@NonNull Country destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    /**
     * Domain service used to set the plane used in the flight.
     * @param planeId - the plane's ID.
     * @param plane - a plane value object representing the actual plane aggregate.
     */
    public void setPlaneId(@NonNull PlaneId planeId, @NonNull Plane plane) {
        this.planeId = planeId;
        setPlane(plane);
    }

    /**
     * Domain service that is called in the previous domain service. It is used to set the plane object.
     * @param plane - a plane value object representing the actual plane aggregate.
     */
    private void setPlane(@NonNull Plane plane) {
        this.plane = plane;
    }

}
