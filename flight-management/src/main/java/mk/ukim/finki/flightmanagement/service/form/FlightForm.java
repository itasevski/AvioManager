package mk.ukim.finki.flightmanagement.service.form;

import lombok.Data;
import mk.ukim.finki.flightmanagement.domain.valueobject.CountryId;
import mk.ukim.finki.flightmanagement.domain.valueobject.FlightDates;
import mk.ukim.finki.flightmanagement.domain.valueobject.PlaneId;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * FlightForm class - class used to cast request bodies into FlightForm instances, later used to create actual Flight entities.
 */
@Data
public class FlightForm {

    @NotNull
    private FlightDates flightDates;

    @NotNull
    private CountryId departureCountryId;

    @NotNull
    private CountryId destinationCountryId;

    @NotNull
    private PlaneId planeId;

    @Valid
    private List<FlightParticipantForm> flightParticipants = new ArrayList<>();

}
