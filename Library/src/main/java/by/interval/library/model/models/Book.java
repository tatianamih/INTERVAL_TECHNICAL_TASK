package by.interval.library.model.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Book extends PrintedEdition{
    Long genreId;
}
