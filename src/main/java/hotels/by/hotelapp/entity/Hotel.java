    package hotels.by.hotelapp.entity;

    import hotels.by.hotelapp.entity.embeeded.Address;
    import hotels.by.hotelapp.entity.embeeded.ArrivalTime;
    import hotels.by.hotelapp.entity.embeeded.Contacts;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;

    @Entity
    @Table(name = "tb_hotels")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Hotel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String description;
        private String brand;
        @Embedded
        private Address address;
        @Embedded
        private Contacts contacts;
        @Embedded
        private ArrivalTime arrivalTime;

        @ElementCollection
        private List<String> amenities;


    }
