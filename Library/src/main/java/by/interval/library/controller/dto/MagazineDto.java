package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MagazineDto implements BaseDto {
    Long id;
    String title;
    Integer pageNumber;
    Integer publicationYear;
    Integer printedEditionNumber;
    Long publishingHouseId;
    Long authorId;
    Long genreId;
    Integer issueNumber;
}

