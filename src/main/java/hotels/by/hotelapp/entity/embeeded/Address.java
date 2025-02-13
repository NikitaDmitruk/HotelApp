package hotels.by.hotelapp.entity.embeeded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    private String houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;

}
