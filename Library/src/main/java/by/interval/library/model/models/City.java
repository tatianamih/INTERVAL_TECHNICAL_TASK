package by.interval.library.model.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class City {
    @Id
    private Long id;

    private String name;
}
