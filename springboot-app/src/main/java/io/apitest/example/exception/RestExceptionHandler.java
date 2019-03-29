package io.apitest.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.Date;

/**
 * Created by prasantabiswas on 29/06/18.
 */

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ViewNotFoundException.class)
    public ResponseEntity<Object> handleViewNotFoundException(ViewNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkflowNotFoundException.class)
    public ResponseEntity<Object> handleWorkflowNotFoundException(WorkflowNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setMessage("The request could not be understood by the server due to malformed syntax");
        errorDetails.setDetails(request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setMessage("Request validation failed");
        errorDetails.setDetails(ex.getBindingResult().toString());
        ex.printStackTrace();
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        errorDetails.setMessage("Method Not Supported");
        errorDetails.setDetails(request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandleFoundException(NoHandlerFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse();
        errorDetails.setTimestamp(new Date());
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage("Method Not Found");
        errorDetails.setDetails(request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }
}
