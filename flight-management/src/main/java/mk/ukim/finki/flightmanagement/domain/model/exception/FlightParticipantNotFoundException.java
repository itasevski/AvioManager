package mk.ukim.finki.flightmanagement.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * FlightParticipantNotFoundException class - an exception class used when a flight participant can't be found in the database. It gets thrown with a 404 response status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightParticipantNotFoundException extends RuntimeException {

    public FlightParticipantNotFoundException(String message) {
        super(message);
    }

}
