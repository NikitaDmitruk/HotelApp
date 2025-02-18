package hotels.by.hotelapp.exception;

public class HotelAlreadyExistException extends RuntimeException {
    public HotelAlreadyExistException() {
    }

    public HotelAlreadyExistException(String message) {
        super(message);
    }
}
