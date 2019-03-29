package io.apitest.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by prasantabiswas on 29/06/18.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public CustomerNotFoundException() {
        super();
    }
}
