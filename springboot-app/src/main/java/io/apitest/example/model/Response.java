package io.apitest.example.model;

/**
 * Created by prasantabiswas on 27/06/18.
 */
public class Response {
    private int status;
    private String message;

    public Response() {
        super();
    }

    public Response(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
