package hotels.by.hotelapp.controller;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.exception.ErrorResponse;
import hotels.by.hotelapp.mapper.HotelMapper;
import hotels.by.hotelapp.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/property-view")
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    public HotelController(HotelService hotelService, HotelMapper hotelMapper) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    @Operation(
            summary = "Create a new hotel",
            description = "Creates a new hotel and returns its short information."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Hotel successfully created",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseShortHotelDto.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Not valid"
    )
    @PostMapping("/hotels")
    public ResponseEntity<ResponseShortHotelDto> createHotel(@Valid @RequestBody CreateHotelDto hotelDto) {
        ResponseShortHotelDto responseShortHotelDto = hotelService.createHotel(hotelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseShortHotelDto);
    }

    @Operation(
            summary = "Add amenities to a hotel",
            description = "Adds a list of amenities to a specified hotel."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Amenities successfully added"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Hotel not found"
    )
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenitiesById(@PathVariable Long id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.ok().build();
    }


    @Operation(
            summary = "Getting an Extended description of the hotel",
            description = "Returns a hotel with extended information"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful response",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseHotelDto.class))
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Hotel not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @ExampleObject(value = "{\"error\": \"Hotel not found\", \"status\": 404}")
            )
    )
    @GetMapping("/hotels/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        if (hotel.isPresent()) {
            ResponseHotelDto responseHotelDto = hotelMapper.toResponseHotelDto(hotel.get());
            return ResponseEntity.ok(responseHotelDto);
        }
        ErrorResponse errorResponse = new ErrorResponse("Hotel not found", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);//Should be exception in service
    }

    @Operation(
            summary = "Getting a list of hotels",
            description = "Returns a list of all hotels with brief information"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful response",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseShortHotelDto.class)),
                    examples = @ExampleObject(value = "[{\"id\": 1, \"name\": \"DoubleTree by Hilton Minsk\", \"description\": \"The DoubleTree by Hilton Hotel Minsk...\", \"address\": \"9 Pobediteley Avenue, Minsk, 220004, Belarus\", \"phone\": \"+375 17 309-80-00\"}]")
            )
    )
    @GetMapping("/hotels")
    public ResponseEntity<List<ResponseShortHotelDto>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        List<ResponseShortHotelDto> hotelsDto = hotels.stream().map(hotelMapper::toResponseShortHotelDto).toList();
        return ResponseEntity.ok(hotelsDto);
    }


    @Operation(
            summary = "Search hotels by parameters",
            description = "Returns a list of hotels matching given parameters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful response"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Hotels not found"
    )
    @GetMapping("/search")
    public ResponseEntity<?> getAllHotelsByParam(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String brand,
                                                 @RequestParam(required = false) String city,
                                                 @RequestParam(required = false) String county,
                                                 @RequestParam(required = false) List<String> amenities) {
        List<ResponseShortHotelDto> shortHotelDtos = hotelService.searchHotels(name, brand, city, county, amenities);
        if (shortHotelDtos.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Hotels not found", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);//Should be exception in service
        }
        return ResponseEntity.ok(shortHotelDtos);
    }

    @Operation(
            summary = "Get a hotel histogram",
            description = "Returns a histogram of hotels grouped by the given parameter."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful response"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid parameter"
    )
    @GetMapping("/histogram/{param}")
    public ResponseEntity<?> getHotelHistogram(@PathVariable String param) {
        Map<String, Long> hotelHistogram = hotelService.getHotelHistogram(param);
        if (hotelHistogram.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Invalid parameter", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);//Should be exception in service
        }
        return ResponseEntity.ok(hotelHistogram);
    }


}
