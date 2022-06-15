package com.example.backend_qlnh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidRequestDataException(String msg) {
        super(msg);
    }

}
