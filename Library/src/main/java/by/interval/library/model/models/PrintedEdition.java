package by.interval.library.model.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.MappedSuperclass;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class PrintedEdition {
    @Id
    Long id;
    String title;
    Integer pageNumber;
    Integer publicationYear;
    Integer printedEditionNumber;
    Long publishingHouseId;
    Long authorId;
}
