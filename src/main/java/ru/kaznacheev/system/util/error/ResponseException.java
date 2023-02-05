package ru.kaznacheev.system.util.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseException extends RuntimeException{

    private final List<String> messages;
    private final HttpStatus httpStatus;
    public ResponseException(List<String> messages, HttpStatus httpStatus) {
        this.messages = messages;
        this.httpStatus = httpStatus;
    }

    public List<String> getMessages() {
        return messages;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
