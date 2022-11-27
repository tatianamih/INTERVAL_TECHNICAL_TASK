package by.interval.library.controller.mappers.impl;


import by.interval.library.controller.dto.GenreCreateDto;
import by.interval.library.controller.dto.GenreDto;
import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the Genre entity to / from DTO
 */
@Component
public class GenreMapper implements GenericMapper<Genre, GenreDto, GenreCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GenreDto toDto(Genre genre) {
        return Objects.isNull(genre) ? null : modelMapper.map(genre, GenreDto.class);
    }

    @Override
    public Genre toEntity(GenreDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Genre.class);
    }

    @Override
    public Genre toEntityMethCreate(GenreCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Genre.class);
    }

    @Override
    public List<GenreDto> toDtoList(List<Genre> entityList) {
        return entityList.stream()
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Genre> toEntityList(List<GenreDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, Genre.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<Genre> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
