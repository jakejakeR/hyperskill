package antifraud.controller;

import antifraud.model.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorDto> exceptionHandler400(RuntimeException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(e.getMessage()));
    }
}
