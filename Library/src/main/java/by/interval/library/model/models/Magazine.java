package by.interval.library.model.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Magazine extends PrintedEdition {
    Long genreId;
    Integer issueNumber;
}
