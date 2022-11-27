package by.interval.library.controller.mappers.impl;



import by.interval.library.controller.dto.*;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the Author entity to / from DTO
 */
@Component
public class AuthorMapper implements GenericMapper<Author, AuthorDto, AuthorCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDto toDto(Author author) {
        return Objects.isNull(author) ? null : modelMapper.map(author,AuthorDto.class);
    }

    @Override
    public Author toEntity(AuthorDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Author.class);
    }

    @Override
    public Author toEntityMethCreate(AuthorCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Author.class);
    }

    @Override
    public List<AuthorDto> toDtoList(List<Author> entityList) {
        return entityList.stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> toEntityList(List<AuthorDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, Author.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<Author> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
