package hotels.by.hotelapp.controller;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property-view")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotels")
    public ResponseEntity<ResponseShortHotelDto> createHotel(@RequestBody CreateHotelDto hotelDto) {
        ResponseShortHotelDto responseShortHotelDto = hotelService.createHotel(hotelDto);
        return ResponseEntity.ok(responseShortHotelDto);
    }

}
