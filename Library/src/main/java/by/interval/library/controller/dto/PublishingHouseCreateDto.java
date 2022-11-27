package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PublishingHouseCreateDto implements BaseDto {
    @NotBlank
    private String name;

    @NotNull
    Long cityId;
}

