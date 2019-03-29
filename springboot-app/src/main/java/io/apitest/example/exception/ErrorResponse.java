package io.apitest.example.exception;

import java.util.Date;

/**
 * Created by prasantabiswas on 29/06/18.
 */
public class ErrorResponse {

    private Date timestamp;
    private int errorCode;
    private String message;
    private String details;

    public ErrorResponse()
    {
        super();
    }

    public ErrorResponse(Date timestamp, int errorCode, String message, String details)
    {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
