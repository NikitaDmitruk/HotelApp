package hotels.by.hotelapp.entity.embeeded;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contacts {

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Phone number", example = "+375 17 309-80-00")
    private String phone;

    @Email
    @Schema(description = "Email", example = "doubletreeminsk.info@hilton.com")
    private String email;
}
