package mk.ukim.finki.flightmanagement.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightParticipantNotFoundException extends RuntimeException {

    public FlightParticipantNotFoundException(String message) {
        super(message);
    }

}
