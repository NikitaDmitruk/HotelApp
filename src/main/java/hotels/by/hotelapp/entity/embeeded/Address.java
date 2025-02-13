package hotels.by.hotelapp.entity.embeeded;

import jakarta.persistence.Embeddable;
import lombok.*;

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
