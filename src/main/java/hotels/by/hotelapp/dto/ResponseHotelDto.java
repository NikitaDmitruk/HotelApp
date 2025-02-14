package hotels.by.hotelapp.dto;

import hotels.by.hotelapp.entity.Hotel;
import hotels.by.hotelapp.entity.embeeded.Address;
import hotels.by.hotelapp.entity.embeeded.ArrivalTime;
import hotels.by.hotelapp.entity.embeeded.Contacts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHotelDto {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;
}
