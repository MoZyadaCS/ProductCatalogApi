package com.fawry.assignment.productcatalog.config;

import com.fawry.assignment.productcatalog.exception.ItemNotAvailableException;
import com.fawry.assignment.productcatalog.exception.MethodNotAllowedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity handleResourceNotFound(NoSuchElementException exception, WebRequest request){
        String responseBody = "The Element That You Search For Does Not Exist";
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(ItemNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // what response status ?
    public ResponseEntity handleItemNotAvailable(ItemNotAvailableException exception, WebRequest request){
        String responseBody = "One Of the products is not available at this moment please check the order again";
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity handleMethodNotAllowed(MethodNotAllowedException exception, WebRequest request){
        String responseBody = "this order is already bought, cannot buy it again";
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

}
