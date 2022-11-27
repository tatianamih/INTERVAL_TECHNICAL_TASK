package by.interval.library.controller.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class GenreCreateDto {

    @NotBlank
    private String name;
}

