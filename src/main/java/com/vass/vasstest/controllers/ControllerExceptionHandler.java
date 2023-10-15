package com.vass.vasstest.controllers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vass.vasstest.dtos.ErrorResponse;
import com.vass.vasstest.exceptions.NoDataFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = NoDataFoundException.class)
    public ResponseEntity<ErrorResponse> noDataFoundException(NoDataFoundException e) {
        return buildErrorResponse(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingRequestParameterException(MissingServletRequestParameterException e) {
        return buildErrorResponse("Missing request parameter: " + e.getParameterName(), BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> genericException() {
        return buildErrorResponse("Internal server error.", INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorResponse(message), httpStatus);
    }
}