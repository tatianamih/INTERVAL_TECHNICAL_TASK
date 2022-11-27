package by.interval.library.controller.controllers;


import by.interval.library.controller.dto.*;
import by.interval.library.controller.mappers.impl.MagazineMapper;
import by.interval.library.model.models.Magazine;
import by.interval.library.service.MagazineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/magazines")
@Log4j2
@RestController
@Api(tags = "Magazines")
public class MagazineController {
    private final MagazineService magazineService;

    private final MagazineMapper magazineMapper;

    @Autowired
    public MagazineController(MagazineService magazineService,
                              MagazineMapper magazineMapper) {
        this.magazineService = magazineService;
        this.magazineMapper = magazineMapper;
    }

    @ApiOperation("Найти все журналы по id автора")
    @GetMapping("/{id}/authors")
    public ResponseEntity<PageDto> getAllByAuthorId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Magazine> bookPage = magazineService.getAllByAuthorId(page, pageSize, sortBy, id);
        PageDto pageDto = magazineMapper.toDtoPage(bookPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все журналы по id жанра")
    @GetMapping("/{id}/genres")
    public ResponseEntity<PageDto> getAllByGenreId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Magazine> magazinePage = magazineService.getAllByGenreId(page, pageSize, sortBy, id);
        PageDto pageDto = magazineMapper.toDtoPage(magazinePage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все журналы по году выпуска")
    @GetMapping("/show-magazines-by-publication-year/{year}")
    public ResponseEntity<PageDto> getAllByPublicationYear
            (@PathVariable("year") Integer year,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Magazine> magazinePage = magazineService.getAllByPublicationYear(page, pageSize, sortBy, year);
        PageDto pageDto = magazineMapper.toDtoPage(magazinePage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все журналы по id издания")
    @GetMapping("/{id}/publishing-houses")
    public ResponseEntity<PageDto> getAllByPublishingHouseId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Magazine> magazinePage = magazineService.getAllByPublishingHouseId(page, pageSize, sortBy, id);
        PageDto pageDto = magazineMapper.toDtoPage(magazinePage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти журналы по title")
    @GetMapping("/show-magazine-by-title/{title}")
    private ResponseEntity<MagazineDto> getByTittle(@PathVariable("title") String title) {
        Magazine magazine = magazineService.getByTittle(title);
        MagazineDto magazineDto = magazineMapper.toDto(magazine);

        return new ResponseEntity<>(magazineDto, HttpStatus.OK);
    }

    @ApiOperation("Найти журнал по id")
    @GetMapping("/{id}")
    private ResponseEntity<MagazineDto> getById(@PathVariable("id") Long id) {
        Magazine magazine = magazineService.getById(id);
        MagazineDto magazineDto = magazineMapper.toDto(magazine);

        return new ResponseEntity<>(magazineDto, HttpStatus.OK);
    }

    @ApiOperation("Удалить журнал по id")
    @PostMapping("/{id}")
    private ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        String response = magazineService.delete(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Найти все журналы")
    @GetMapping()
    public ResponseEntity<PageDto> getAll
            (@RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "title") String sortBy) {
        Page<Magazine> magazinePage = magazineService.getAll(page, pageSize, sortBy);
        PageDto pageDto = magazineMapper.toDtoPage(magazinePage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить журнал")
    @PostMapping()
    private ResponseEntity<MagazineDto> create(@Valid @RequestBody MagazineCreateDto magazineCreateDto) {
        Magazine magazine = magazineMapper.toEntityMethCreate(magazineCreateDto);
        magazine = magazineService.save(magazine);
        MagazineDto dto = magazineMapper.toDto(magazine);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation("Изменить данные журнала")
    @PutMapping("/{id}")
    ResponseEntity<MagazineDto> update(@PathVariable("id") Long id,
                                       @Valid @RequestBody MagazineCreateDto dto) {
        Magazine magazine = magazineMapper.toEntityMethCreate(dto);
        Magazine newMagazine = magazineService.update(magazine, id);
        MagazineDto newDto = magazineMapper.toDto(newMagazine);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все журналы по номеру выпуска")
    @GetMapping("/show-magazines-by-issue-number/{issueNumber}")
    public ResponseEntity<PageDto> getAllByIssueNumber
            (@PathVariable("issueNumber") Integer issueNumber,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Magazine> magazinePage = magazineService.getAllByIssueNumber(page, pageSize, sortBy, issueNumber);
        PageDto pageDto = magazineMapper.toDtoPage(magazinePage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}
