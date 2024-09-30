package exp3_s8.envios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnvioNotFoundException extends RuntimeException {

    public EnvioNotFoundException(String message) {
        super(message);
    }
}