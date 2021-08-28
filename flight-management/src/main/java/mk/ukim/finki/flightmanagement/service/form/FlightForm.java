package mk.ukim.finki.flightmanagement.service.form;

import lombok.Data;
import mk.ukim.finki.flightmanagement.domain.valueobject.CountryId;
import mk.ukim.finki.flightmanagement.domain.valueobject.FlightDates;
import mk.ukim.finki.flightmanagement.domain.valueobject.PlaneId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlightForm {

    @NotNull
    private FlightDates flightDates;

    @NotNull
    private CountryId departureCountry;

    @NotNull
    private CountryId destinationCountry;

    @NotNull
    private PlaneId planeId;

    @Valid
    @NotEmpty
    private List<FlightParticipantForm> items = new ArrayList<>();

}
