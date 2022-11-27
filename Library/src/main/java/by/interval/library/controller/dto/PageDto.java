package by.interval.library.controller.dto;

import by.interval.library.controller.dto.interf.BaseDto;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PageDto {

    private ArrayList<BaseDto> list;

    private Integer totalPages;
}
