package by.interval.library.service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Long id) {
        super(message + id);
    }
    public ResourceNotFoundException(String message, Integer value) {
        super(message + value);
    }
    public ResourceNotFoundException(String message, String value) {
        super(message + value);
    }

    public void getMessage(String message, String name) {
    }
}

