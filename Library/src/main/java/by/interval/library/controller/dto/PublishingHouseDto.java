package by.interval.library.controller.dto;


import by.interval.library.controller.dto.interf.BaseDto;
import by.interval.library.model.models.City;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class PublishingHouseDto implements BaseDto {
    private Long id;

    @NotBlank
    private String name;

    private Long cityId;
}

