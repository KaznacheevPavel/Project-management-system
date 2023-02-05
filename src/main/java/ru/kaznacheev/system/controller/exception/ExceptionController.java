package ru.kaznacheev.system.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kaznacheev.system.util.error.ErrorResponse;
import ru.kaznacheev.system.util.error.ResponseException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ResponseException exception) {
        ErrorResponse response = new ErrorResponse(new Timestamp(System.currentTimeMillis()), exception.getMessages());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ConstraintViolationException exception) {
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            messages.add(constraintViolation.getMessage());
        }
        ErrorResponse response = new ErrorResponse(new Timestamp(System.currentTimeMillis()), messages);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
