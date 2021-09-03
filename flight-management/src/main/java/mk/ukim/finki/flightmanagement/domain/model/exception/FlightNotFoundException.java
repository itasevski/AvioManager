package mk.ukim.finki.flightmanagement.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * FlightNotFoundException class - an exception class used when a flight can't be found in the database. It gets thrown with a 404 response status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(String message) {
        super(message);
    }

}
