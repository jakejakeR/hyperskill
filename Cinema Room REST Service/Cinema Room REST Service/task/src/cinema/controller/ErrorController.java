package cinema.controller;

import cinema.exception.InvalidTokenException;
import cinema.exception.OutOfBoundsException;
import cinema.exception.SeatNotAvailableException;
import cinema.model.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({
            OutOfBoundsException.class,
            SeatNotAvailableException.class,
            InvalidTokenException.class
    })
    ResponseEntity<ErrorDto> exceptionHandler(RuntimeException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(e.getMessage()));
    }
}
