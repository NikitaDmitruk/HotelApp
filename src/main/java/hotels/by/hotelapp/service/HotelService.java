package hotels.by.hotelapp.service;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.exception.HotelNotFoundException;
import hotels.by.hotelapp.mapper.HotelMapper;
import hotels.by.hotelapp.repository.HotelRepository;
import hotels.by.hotelapp.specification.HotelSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final EntityManager entityManager;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper, EntityManager entityManager) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.entityManager = entityManager;
    }

    public Hotel updateHotel(CreateHotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        return hotelRepository.save(hotel);
    }

    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }

    public ResponseShortHotelDto createHotel(CreateHotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
//        if(hotelRepository.existsByName(hotel.getName())) {
//            throw new HotelAlreadyExistException("Hotel already exists"); // Could be a custom exception
//        }
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponseShortHotelDto(savedHotel);
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }


    public void addAmenities(Long id, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);
        hotel.setAmenities(amenities);
        hotelRepository.save(hotel);
    }

    public List<ResponseShortHotelDto> searchHotels(String name, String brand, String city, String county, List<String> amenities) {
        Specification<Hotel> spec = HotelSpecification.searchHotels(name, brand, city, county, amenities);
        List<Hotel> hotels = hotelRepository.findAll(spec);
        return hotels.stream()
                .map(hotelMapper::toResponseShortHotelDto)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getHotelHistogram(String param) {
        if (param == null || param.isBlank()) {
            throw new IllegalArgumentException("Parameter cannot be null or empty");
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Hotel> hotel = query.from(Hotel.class);

        if ("amenities".equalsIgnoreCase(param)) {
            return getAmenitiesHistogram(cb, query, hotel);
        }
        Path<Object> groupByField = switch (param.toLowerCase()) {
            case "brand" -> hotel.get("brand");
            case "city" -> hotel.get("address").get("city");
            case "county" -> hotel.get("address").get("county");
            default -> throw new IllegalArgumentException("Unsupported parameter: " + param);
        };
        query.multiselect(groupByField, cb.count(hotel)).groupBy(groupByField);
        return executeQuery(query);
    }

    private Map<String, Long> getAmenitiesHistogram(CriteriaBuilder cb, CriteriaQuery<Object[]> query, Root<Hotel> hotel) {
        Join<Hotel, String> amenitiesJoin = hotel.join("amenities");
        query.multiselect(amenitiesJoin, cb.count(hotel)).groupBy(amenitiesJoin);
        return executeQuery(query);
    }

    private Map<String, Long> executeQuery(CriteriaQuery<Object[]> query) {
        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        entry -> (String) entry[0],
                        entry -> (Long) entry[1]
                ));
    }
}
