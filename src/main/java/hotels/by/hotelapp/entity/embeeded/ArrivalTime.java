package hotels.by.hotelapp.entity.embeeded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArrivalTime {

    private Date checkIn;

    private Date checkOut;
}
