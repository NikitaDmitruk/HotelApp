package hotels.by.hotelapp.dto;

import hotels.by.hotelapp.entity.embeeded.Address;
import hotels.by.hotelapp.entity.embeeded.ArrivalTime;
import hotels.by.hotelapp.entity.embeeded.Contacts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for creating a hotel")
public class CreateHotelDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Hotel name", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Hotel description", example = "Luxury hotel with city views")
    private String description;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Hotel brand", example = "Hilton")
    private String brand;

    @Valid
    @NotNull
    @Schema(description = "Hotel address")
    private Address address;

    @Valid
    @NotNull
    @Schema(description = "Hotel contact details")
    private Contacts contacts;

    @Valid
    @NotNull
    @Schema(description = "Arrival time details")
    private ArrivalTime arrivalTime;
}

