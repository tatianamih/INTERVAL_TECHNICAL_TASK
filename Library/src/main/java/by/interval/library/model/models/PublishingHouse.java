package by.interval.library.model.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PublishingHouse {
    @Id
    Long id;
    String name;
    Long cityId;
}
