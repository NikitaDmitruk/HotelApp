package hotels.by.hotelapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HotelAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleHotelAlreadyExistException(HotelAlreadyExistException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse response = new ErrorResponse("Not valid", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException e) {
        ErrorResponse response = new ErrorResponse("Hotel not found", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
