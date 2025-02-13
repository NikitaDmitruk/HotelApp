package hotels.by.hotelapp.service;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.mapper.HotelMapper;
import hotels.by.hotelapp.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    public Hotel updateHotel(CreateHotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        return hotelRepository.save(hotel);
    }

    public void deleteHotelById(Long id) {
    }

    public ResponseShortHotelDto createHotel(CreateHotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponse(savedHotel);

    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }


}
