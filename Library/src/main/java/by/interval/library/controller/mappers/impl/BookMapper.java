package by.interval.library.controller.mappers.impl;


import by.interval.library.controller.dto.*;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.Author;
import by.interval.library.model.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the Book entity to / from DTO
 */
@Component
public class BookMapper implements GenericMapper<Book, BookDto, BookCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto toDto(Book book) {
        return Objects.isNull(book) ? null : modelMapper.map(book,BookDto.class);
    }

    @Override
    public Book toEntity(BookDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Book.class);
    }

    @Override
    public Book toEntityMethCreate(BookCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Book.class);
    }

    @Override
    public List<BookDto> toDtoList(List<Book> entityList) {
        return entityList.stream()
                .map(book-> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> toEntityList(List<BookDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, Book.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<Book> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
