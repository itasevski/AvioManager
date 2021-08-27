package mk.ukim.finki.flightmanagement.domain.valueobject;

import lombok.Getter;
import mk.ukim.finki.flightmanagement.FlightManagementApplication;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * FlightDates class - an immutable date and time value object for Flight entities.
 */
@Getter
@Embeddable
public class FlightDates implements ValueObject {

    private final String departureDate;
    private final String arrivalDate;

    protected FlightDates() {
        this.departureDate = "";
        this.arrivalDate = "";
    }

    public FlightDates(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.departureDate = FlightManagementApplication.formatter.format(departureDate);
        this.arrivalDate = FlightManagementApplication.formatter.format(arrivalDate);
    }

    public static FlightDates valueOf(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        return new FlightDates(departureDate, arrivalDate);
    }

    public FlightDates addDaysToDepartureDate(int days) {
        return new FlightDates(LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter).plusDays(days),
                LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter));
    }

    public FlightDates subtractDaysFromDepartureDate(int days) {
        return new FlightDates(LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter).minusDays(days),
                LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter));
    }

    public FlightDates addDaysToArrivalDate(int days) {
        return new FlightDates(LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter),
                LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter).plusDays(days));
    }

    public FlightDates subtractDaysFromArrivalDate(int days) {
        return new FlightDates(LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter),
                LocalDateTime.parse(this.departureDate, FlightManagementApplication.formatter).minusDays(days));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        FlightDates flightDates = (FlightDates) obj;
        return this.departureDate.equals(flightDates.departureDate) && this.arrivalDate.equals(flightDates.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.departureDate + this.arrivalDate);
    }

}
