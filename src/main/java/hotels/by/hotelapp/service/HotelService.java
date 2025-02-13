package hotels.by.hotelapp.service;

import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }
}
