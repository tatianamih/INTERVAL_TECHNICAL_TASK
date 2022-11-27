package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MagazineCreateDto implements BaseDto {
    @JsonIgnore
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

