package mk.ukim.finki.flightmanagement.service.form;

import lombok.Data;
import mk.ukim.finki.flightmanagement.domain.valueobject.Person;

import javax.validation.constraints.NotNull;

@Data
public class FlightParticipantForm {

    @NotNull
    private Person person;

}
