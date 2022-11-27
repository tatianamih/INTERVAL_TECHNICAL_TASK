package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MagazineCreateDto implements BaseDto {
    @JsonIgnore
    Long id;

    @NotBlank
    String title;

    @NotNull
    Integer pageNumber;

    @NotNull
    Integer publicationYear;

    @NotNull
    Integer printedEditionNumber;

    @NotNull
    Long publishingHouseId;

    @NotNull
    Long authorId;

    @NotNull
    Long genreId;

    @NotNull
    Integer issueNumber;
}

