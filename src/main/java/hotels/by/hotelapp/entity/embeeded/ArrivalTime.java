package hotels.by.hotelapp.entity.embeeded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArrivalTime {

    private LocalTime checkIn;

    private LocalTime checkOut;
}
