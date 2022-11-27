package by.interval.library.controller.controllers;


import by.interval.library.controller.dto.GenreCreateDto;
import by.interval.library.controller.dto.GenreDto;
import by.interval.library.controller.mappers.impl.GenreMapper;
import by.interval.library.model.models.Genre;
import by.interval.library.repository.GenreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/genres")
@Log4j2
@RestController
@Api(tags = "Genre")
public class GenreController {

    private final GenreRepository genreService;
    private final GenreMapper genreMapper;

   @Autowired
    public GenreController(GenreRepository genreService, GenreMapper genreMapper) {
       this.genreService = genreService;
       this.genreMapper = genreMapper;
   }

    @ApiOperation("Добавить жанр")
    @PostMapping()
    private ResponseEntity<GenreDto> create(@Valid @RequestBody GenreCreateDto dto) {
        Genre genre = genreMapper.toEntityMethCreate(dto);
        genre =  genreService.save(genre);
        GenreDto newDto = genreMapper.toDto(genre);

        return new ResponseEntity<>(newDto, HttpStatus.CREATED);
    }

    @ApiOperation("Найти все жанры")
    @GetMapping()
    ResponseEntity<List<GenreDto>> getAll() {
        List<Genre> genres = genreService.findAll();
        List<GenreDto> genreDtos = genreMapper.toDtoList(genres);

        return new ResponseEntity<>(genreDtos, HttpStatus.OK);
    }

    @ApiOperation("Найти жанр по id")
    @GetMapping("/{id}")
    private ResponseEntity<GenreDto> getById(@PathVariable Long id) {
        Genre genre = genreService.findById(id);
        GenreDto genreDto = genreMapper.toDto(genre);

        return new ResponseEntity<>(genreDto, HttpStatus.OK);
    }

    @ApiOperation("Изменить название жанра")
    @PutMapping("/{id}")
    ResponseEntity<GenreDto> update(@PathVariable Long id,
                                   @Valid @RequestBody GenreDto dto) {
        Genre genre = genreMapper.toEntity(dto);
        genreService.update(genre, id);
        GenreDto newDto = genreMapper.toDto(genre);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }
}
