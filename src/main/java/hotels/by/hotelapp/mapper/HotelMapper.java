package hotels.by.hotelapp.mapper;

import hotels.by.hotelapp.dto.CreateHotelDto;
import hotels.by.hotelapp.dto.ResponseHotelDto;
import hotels.by.hotelapp.dto.ResponseShortHotelDto;
import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.entity.embeeded.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(CreateHotelDto hotelDto);

    ResponseHotelDto toResponseHotelDto(Hotel hotel);

    @Mapping(target = "address", expression = "java(addressToString(hotel.getAddress()))")
    @Mapping(target = "phone", source = "contacts.phone")
    ResponseShortHotelDto toResponseShortHotelDto(Hotel hotel);

    default String addressToString(Address address) {
        return address.toString();
    }

}
