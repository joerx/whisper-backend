package io.yodo.whisper.endpoint;

import io.yodo.whisper.error.ErrorResponse;
import io.yodo.whisper.error.InvalidRequestException;
import io.yodo.whisper.error.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleError(ConstraintViolationException e) {
        return createResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleError(Exception e) {
        return createResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleError(InvalidRequestException e) {
        return createResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleError(NoSuchEntityException e) {
        return createResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleError(HttpRequestMethodNotSupportedException e) {
        return createResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ResponseEntity<ErrorResponse> createResponse(Exception e, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(e), status);
    }
}
