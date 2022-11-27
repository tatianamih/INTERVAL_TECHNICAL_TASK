package by.interval.library.controller.controllers;



import by.interval.library.controller.dto.*;
import by.interval.library.controller.mappers.impl.AuthorMapper;
import by.interval.library.model.models.Author;
import by.interval.library.model.models.City;
import by.interval.library.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/authors")
@Log4j2
@RestController
@Api(tags = "Author")
public class AuthorController {
    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @ApiOperation("Найти автора по id")
    @GetMapping("/{id}")
    private ResponseEntity<AuthorDto> getById(@PathVariable("id") Long id) {
        Author author = authorService.getById(id);
        AuthorDto authorDto = authorMapper.toDto(author);

        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить автора")
    @PostMapping()
    private ResponseEntity<AuthorDto> create(@Valid @RequestBody AuthorCreateDto authorCreateDto) {
        Author author = authorMapper.toEntityMethCreate(authorCreateDto);
        author = authorService.save(author);
        AuthorDto dto = authorMapper.toDto(author);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation("Найти всех авторов")
    @GetMapping()
    public ResponseEntity<PageDto> getAll
            (@RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "name") String sortBy) {

        Page<Author> authorPage = authorService.getAll(page, pageSize, sortBy);
        PageDto pageDto = authorMapper.toDtoPage(authorPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Изменить данные автора")
    @PutMapping("/{id}")
    ResponseEntity<AuthorDto> update(@PathVariable("id") Long id,
                                   @Valid @RequestBody AuthorCreateDto dto) {
        Author author = authorMapper.toEntityMethCreate(dto);
        Author newAuthor = authorService.update(author, id);
        AuthorDto newDto = authorMapper.toDto(newAuthor);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }
}
