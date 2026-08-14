package ao.grupowedo.emailssignature.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ao.grupowedo.emailssignature.exception.EmployeeNotFoundExeption;
import ao.grupowedo.emailssignature.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundExeption.class)
    public ResponseEntity<ErrorResponse>   handleEmployeeNotFound(EmployeeNotFoundExeption exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
