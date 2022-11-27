package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class BookDto implements BaseDto {
    Long id;
    String title;
    Integer pageNumber;
    Integer publicationYear;
    Integer printedEditionNumber;
    Long publishingHouseId;
    Long authorId;
    Long genreId;
}

