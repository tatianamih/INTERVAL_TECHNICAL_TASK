package by.interval.library.model.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Genre {
   @Id
    Long id;
    String name;
}
