package hotels.by.hotelapp.entity.embeeded;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "House number", example = "9")
    private String houseNumber;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Street name", example = "Pobediteley")
    private String street;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "City name", example = "Minsk")
    private String city;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "County name", example = "Belarus")
    private String county;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Post code", example = "220004")
    private String postCode;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str
                .append(houseNumber)
                .append(" ")
                .append(street)
                .append(", ")
                .append(city)
                .append(", ")
                .append(postCode)
                .append(", ")
                .append(county);
        return str.toString();
    }
}
