package hotels.by.hotelapp.dto;

import hotels.by.hotelapp.entity.embeeded.Address;
import hotels.by.hotelapp.entity.embeeded.ArrivalTime;
import hotels.by.hotelapp.entity.embeeded.Contacts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Extended description of the hotel")
public class ResponseHotelDto {

    @Schema(description = "Hotel ID", example = "1")
    private Long id;
    @Schema(description = "Hotel name", example = "DoubleTree by Hilton Minsk")
    private String name;
    @Schema(description = "Hotel brand", example = "Hilton")
    private String brand;
    @Schema(description = "Hotel address")
    private Address address;
    @Schema(description = "Hotel contact details")
    private Contacts contacts;
    @Schema(description = "Arrival time details")
    private ArrivalTime arrivalTime;
    @Schema(description = "Hotel amenities")
    private List<String> amenities;
}
