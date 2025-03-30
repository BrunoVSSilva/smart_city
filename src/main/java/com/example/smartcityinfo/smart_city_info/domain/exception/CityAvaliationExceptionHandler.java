package com.example.smartcityinfo.smart_city_info.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CityAvaliationExceptionHandler {

    @ExceptionHandler(CityAvaliationNotFoundException.class)
    public ResponseEntity<String> handleCityAvaliationNotFound(CityAvaliationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCityAvaliationException.class)
    public ResponseEntity<String> handleInvalidCityAvaliation(InvalidCityAvaliationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
