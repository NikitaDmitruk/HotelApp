package hotels.by.hotelapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Short description of the hotel")
public class ResponseShortHotelDto {

    @Schema(description = "Hotel ID", example = "1")
    private Long id;

    @Schema(description = "Hotel name", example = "DoubleTree by Hilton Minsk")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Hotel description (could be null)", example = "The DoubleTree by Hilton Hotel Minsk...")
    private String description;

    @Schema(description = "Hotel address", example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    private String address;

    @Schema(description = "Hotel phone number", example = "+375 17 309-80-00")
    private String phone;
}