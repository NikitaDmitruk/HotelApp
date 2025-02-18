package hotels.by.hotelapp.controller;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.mapper.HotelMapper;
import hotels.by.hotelapp.service.HotelService;
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

    @PostMapping("/hotels")
    public ResponseEntity<ResponseShortHotelDto> createHotel(@RequestBody CreateHotelDto hotelDto) {
        ResponseShortHotelDto responseShortHotelDto = hotelService.createHotel(hotelDto);
        return ResponseEntity.ok(responseShortHotelDto);
    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenitiesById(@PathVariable Long id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<ResponseHotelDto> getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        if (hotel.isPresent()) {
            ResponseHotelDto responseHotelDto = hotelMapper.toResponseHotelDto(hotel.get());
            return ResponseEntity.ok(responseHotelDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<ResponseShortHotelDto>> getAllHotels() {
        List<ResponseShortHotelDto> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseShortHotelDto>> getAllHotelsByParam(@RequestParam(required = false) String name,
                                                                           @RequestParam(required = false) String brand,
                                                                           @RequestParam(required = false) String city,
                                                                           @RequestParam(required = false) String county,
                                                                           @RequestParam(required = false) List<String> amenities) {
        List<ResponseShortHotelDto> shortHotelDtos = hotelService.searchHotels(name, brand, city, county, amenities);
        if (shortHotelDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shortHotelDtos);
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHotelHistogram(@PathVariable String param) {
        Map<String, Long> hotelHistogram = hotelService.getHotelHistogram(param);
        if (hotelHistogram.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(hotelHistogram);
    }


}
