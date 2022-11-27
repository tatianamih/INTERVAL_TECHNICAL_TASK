package by.interval.library.controller.mappers.impl;


import by.interval.library.controller.dto.NewspaperCreateDto;
import by.interval.library.controller.dto.NewspaperDto;
import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.Newspaper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the Newspaper entity to / from DTO
 */
@Component
public class NewspaperMapper implements GenericMapper<Newspaper, NewspaperDto, NewspaperCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public NewspaperDto toDto(Newspaper newspaper) {
        return Objects.isNull(newspaper) ? null : modelMapper.map(newspaper,NewspaperDto.class);
    }

    @Override
    public Newspaper toEntity(NewspaperDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Newspaper.class);
    }

    @Override
    public Newspaper toEntityMethCreate(NewspaperCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Newspaper.class);
    }

    @Override
    public List<NewspaperDto> toDtoList(List<Newspaper> entityList) {
        return entityList.stream()
                .map(newspaper-> modelMapper.map(newspaper, NewspaperDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Newspaper> toEntityList(List<NewspaperDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, Newspaper.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<Newspaper> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(newspaper -> modelMapper.map(newspaper, NewspaperDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
