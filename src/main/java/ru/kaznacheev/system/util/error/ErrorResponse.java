package ru.kaznacheev.system.util.error;

import java.sql.Timestamp;
import java.util.List;

public class ErrorResponse {

    private Timestamp timestamp;
    private List<String> messages;

    public ErrorResponse(Timestamp timestamp, List<String> messages) {
        this.timestamp = timestamp;
        this.messages = messages;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getMessage() {
        return messages;
    }

    public void setMessage(List<String> messages) {
        this.messages = messages;
    }
}
