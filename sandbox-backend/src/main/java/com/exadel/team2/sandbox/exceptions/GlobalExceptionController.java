package com.exadel.team2.sandbox.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<Object> eventNotFound (EventNotFoundException eventNotFoundException){
        return new ResponseEntity<>("Event not found! ", HttpStatus.NOT_FOUND);
    }
}
