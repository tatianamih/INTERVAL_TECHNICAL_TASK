package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class AuthorDto implements BaseDto {
    @NotNull
    private Long id;

    @NotBlank
    private String name;
}

