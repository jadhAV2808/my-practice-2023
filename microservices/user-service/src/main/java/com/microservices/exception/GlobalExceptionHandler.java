package com.microservices.exception;

import com.microservices.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){

        String message=exception.getMessage();
        ApiResponse response=new ApiResponse();
        response.setMessage(message);
        response.setSuccess(true);
        response.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
