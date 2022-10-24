package es.itx.shop.controller.exceptions;

import es.itx.prices.domain.exceptions.PriceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PriceNotFound.class)
    public ResponseEntity<ErrorResponse> notFound(PriceNotFound ex) {
        final var errorResponse =
                new ErrorResponse(ErrorCodeResponse.PRICE_NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DateTimeParseException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> dateInvalidFormat(Exception ex) {
        final var errorResponse =
                new ErrorResponse(ErrorCodeResponse.INVALID_FORMAT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
