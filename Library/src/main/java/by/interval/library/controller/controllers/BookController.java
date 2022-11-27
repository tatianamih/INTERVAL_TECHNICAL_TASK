package by.interval.library.controller.controllers;


import by.interval.library.controller.dto.BookCreateDto;
import by.interval.library.controller.dto.BookDto;
import by.interval.library.controller.dto.CityDto;
import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.mappers.impl.BookMapper;
import by.interval.library.model.models.Book;
import by.interval.library.model.models.City;
import by.interval.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/books")
@Log4j2
@RestController
@Api(tags = "Books")
public class BookController {
    private final BookService bookService;

    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @ApiOperation("Найти все книги по id автора")
    @GetMapping("/{id}/authors")
    public ResponseEntity<PageDto> getAllByAuthorId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Book> bookPage = bookService.getAllByAuthorId(page, pageSize, sortBy, id);
        PageDto pageDto = bookMapper.toDtoPage(bookPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все книги по id жанра")
    @GetMapping("/{id}/genres")
    public ResponseEntity<PageDto> getAllByGenreId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Book> bookPage = bookService.getAllByGenreId(page, pageSize, sortBy, id);
        PageDto pageDto = bookMapper.toDtoPage(bookPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все книги по году выпуска")
    @GetMapping("/show-books-by-publication-year/{year}")
    public ResponseEntity<PageDto> getAllByPublicationYear
            (@PathVariable("year") Integer year,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Book> bookPage = bookService.getAllByPublicationYear(page, pageSize, sortBy, year);
        PageDto pageDto = bookMapper.toDtoPage(bookPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все книги по id издания")
    @GetMapping("/{id}/publishing-houses")
    public ResponseEntity<PageDto> getAllByPublishingHouseId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Book> bookPage = bookService.getAllByPublishingHouseId(page, pageSize, sortBy, id);
        PageDto pageDto = bookMapper.toDtoPage(bookPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти книгу по title")
    @GetMapping("/show-book-by-title/{title}")
    private ResponseEntity<BookDto> getByTittle(@PathVariable("title") String title) {
        Book book = bookService.getByTittle(title);
        BookDto bookDto = bookMapper.toDto(book);

        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @ApiOperation("Найти книгу по id")
    @GetMapping("/{id}")
    private ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        Book book = bookService.getById(id);
        BookDto bookDto = bookMapper.toDto(book);

        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @ApiOperation("Удалить книгу по id")
    @PostMapping("/{id}")
    private ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        String response = bookService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Найти все книги")
    @GetMapping()
    public ResponseEntity<PageDto> getAll
            (@RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "title") String sortBy) {
        Page<Book> bookPage = bookService.getAll(page, pageSize, sortBy);
        PageDto pageDto = bookMapper.toDtoPage(bookPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить книгу")
    @PostMapping()
    private ResponseEntity<BookDto> create(@Valid @RequestBody BookCreateDto bookCreateDto) {
        Book book = bookMapper.toEntityMethCreate(bookCreateDto);
        book = bookService.save(book);
        BookDto dto = bookMapper.toDto(book);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @ApiOperation("Изменить данные о книге")
    @PutMapping("/{id}")
    ResponseEntity<BookDto> update(@PathVariable("id") Long id,
                                   @Valid @RequestBody BookCreateDto dto) {
        Book book = bookMapper.toEntityMethCreate(dto);
        Book newBook = bookService.update(book, id);
        BookDto newDto = bookMapper.toDto(newBook);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }

}
