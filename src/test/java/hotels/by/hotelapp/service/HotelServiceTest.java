package hotels.by.hotelapp.service;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.entity.embeeded.Address;
import hotels.by.hotelapp.entity.embeeded.ArrivalTime;
import hotels.by.hotelapp.entity.embeeded.Contacts;
import hotels.by.hotelapp.exception.HotelNotFoundException;
import hotels.by.hotelapp.mapper.HotelMapper;
import hotels.by.hotelapp.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelService hotelService;

    private Hotel hotel;
    private CreateHotelDto createHotelDto;
    private ResponseShortHotelDto responseShortHotelDto;

    @BeforeEach
    public void setUp() {
        createHotelDto = new CreateHotelDto();
        createHotelDto.setName("DoubleTree by Hilton Minsk");
        createHotelDto.setDescription("Luxury hotel with city views");
        createHotelDto.setBrand("Hilton");
        createHotelDto.setAddress(new Address("11", "Krasnaya Street", "Minsk", "Belarus", "220000"));
        createHotelDto.setContacts(new Contacts("+375 29 123 45 67", "info@hilton.com"));
        createHotelDto.setArrivalTime(new ArrivalTime(LocalTime.parse("14:00"), LocalTime.parse("14:00")));

        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("DoubleTree by Hilton Minsk");
        hotel.setDescription("Luxury hotel with city views");
        hotel.setBrand("Hilton");
        hotel.setAddress(new Address("11", "Krasnaya Street", "Minsk", "Belarus", "220000"));
        hotel.setContacts(new Contacts("+375 29 123 45 67", "info@hilton.com"));
        hotel.setArrivalTime(new ArrivalTime(LocalTime.parse("14:00"), LocalTime.parse("14:00")));

        responseShortHotelDto = new ResponseShortHotelDto();
        responseShortHotelDto.setId(1L);
        responseShortHotelDto.setName("DoubleTree by Hilton Minsk");
        responseShortHotelDto.setDescription("Luxury hotel with city views");
        responseShortHotelDto.setAddress("11 Krasnaya Street, Minsk, 220100, Belarus");
        responseShortHotelDto.setPhone("+375 29 123 45 67");
    }

    @Test
    public void testCreateHotel_Success() {
        when(hotelMapper.toEntity(createHotelDto)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(hotelMapper.toResponseShortHotelDto(hotel)).thenReturn(responseShortHotelDto);

        ResponseShortHotelDto result = hotelService.createHotel(createHotelDto);

        assertEquals(1L, result.getId());
        assertEquals("DoubleTree by Hilton Minsk", result.getName());
        assertEquals("Luxury hotel with city views", result.getDescription());
        assertEquals("11 Krasnaya Street, Minsk, 220100, Belarus", result.getAddress());
        assertEquals("+375 29 123 45 67", result.getPhone());

        verify(hotelMapper, times(1)).toEntity(createHotelDto);
        verify(hotelRepository, times(1)).save(hotel);
        verify(hotelMapper, times(1)).toResponseShortHotelDto(hotel);
    }


    @Test
    public void testAddAmenities_HotelNotFound() {

        Long hotelId = 1L;
        List<String> amenities = List.of("Free WiFi", "Parking");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        assertThrows(HotelNotFoundException.class, () -> hotelService.addAmenities(hotelId, amenities));

        verify(hotelRepository, times(1)).findById(hotelId);
        verify(hotelRepository, never()).save(any(Hotel.class));
    }
}