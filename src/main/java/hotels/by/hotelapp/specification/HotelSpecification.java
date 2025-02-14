package hotels.by.hotelapp.specification;

import hotels.by.hotelapp.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {
    public static Specification<Hotel> searchHotels(String name, String brand, String city, String county, List<String> amenities) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (brand != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            }
            if (city != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address").get("city")), "%" + city.toLowerCase() + "%"));
            }
            if (county != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address").get("county")), "%" + county.toLowerCase() + "%"));
            }
            if (amenities != null && !amenities.isEmpty()) {
                List<Predicate> amenityPredicates = new ArrayList<>();
                for (String amenity : amenities) {
                    String lowerAmenity = amenity.toLowerCase();
                    Predicate amenityPredicate = criteriaBuilder.equal(
                            criteriaBuilder.lower(root.join("amenities")),
                            lowerAmenity
                    );
                    amenityPredicates.add(amenityPredicate);
                }
                predicates.add(criteriaBuilder.or(amenityPredicates.toArray(new Predicate[0])));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}