package mk.ukim.finki.planes.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PlaneNotFoundException class - an exception class used when a plane can't be found in the database. It gets thrown with a 404 response status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlaneNotFoundException extends RuntimeException {

    public PlaneNotFoundException(String message) {
        super(message);
    }

}
