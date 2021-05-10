package com.intertecintl.demo.error;

import com.intertecintl.demo.error.exception.EmployeeNotFoundException;
import com.intertecintl.demo.error.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerHandler
{
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<Error> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request)
    {

        Error body = Error.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .build();

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
