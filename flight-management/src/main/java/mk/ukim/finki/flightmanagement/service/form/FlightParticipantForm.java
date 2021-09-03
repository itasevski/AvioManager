package mk.ukim.finki.flightmanagement.service.form;

import lombok.Data;
import mk.ukim.finki.flightmanagement.domain.valueobject.PersonId;

import javax.validation.constraints.NotNull;

/**
 * FlightParticipantForm class - class used to cast request bodies into FlightParticipantForm instances, later used to create actual FlightParticipant entities.
 */
@Data
public class FlightParticipantForm {

    @NotNull
    private PersonId personId;

}
