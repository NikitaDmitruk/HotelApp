package hotels.by.hotelapp.dto;

import hotels.by.hotelapp.entity.embeeded.Address;
import hotels.by.hotelapp.entity.embeeded.ArrivalTime;
import hotels.by.hotelapp.entity.embeeded.Contacts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelDto {

    private String name;
    private String description;
    private String brand;
    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
}
