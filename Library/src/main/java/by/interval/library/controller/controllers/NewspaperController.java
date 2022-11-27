package by.interval.library.controller.controllers;


import by.interval.library.controller.dto.*;
import by.interval.library.controller.mappers.impl.MagazineMapper;
import by.interval.library.controller.mappers.impl.NewspaperMapper;
import by.interval.library.model.models.Magazine;
import by.interval.library.model.models.Newspaper;
import by.interval.library.service.MagazineService;
import by.interval.library.service.NewspaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/newspapers")
@Log4j2
@RestController
@Api(tags = "Newspaper")
public class NewspaperController {
    private final NewspaperService newspaperService;

    private final NewspaperMapper newspaperMapper;

    @Autowired
    public NewspaperController(NewspaperService newspaperService,
                               NewspaperMapper newspaperMapper) {
        this.newspaperService = newspaperService;
        this.newspaperMapper = newspaperMapper;
    }

    @ApiOperation("Найти все газеты по id автора")
    @GetMapping("/{id}/authors")
    public ResponseEntity<PageDto> getAllByAuthorId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Newspaper> newspaperPage = newspaperService.getAllByAuthorId(page, pageSize, sortBy, id);
        PageDto pageDto = newspaperMapper.toDtoPage(newspaperPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все газеты по году выпуска")
    @GetMapping("/show-newspaper-by-publication-year/{year}")
    public ResponseEntity<PageDto> getAllByPublicationYear
            (@PathVariable("year") Integer year,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Newspaper> newspaperPage = newspaperService.getAllByPublicationYear(page, pageSize, sortBy, year);
        PageDto pageDto = newspaperMapper.toDtoPage(newspaperPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все газеты по id издания")
    @GetMapping("/{id}/publishing-houses")
    public ResponseEntity<PageDto> getAllByPublishingHouseId
            (@PathVariable("id") Long id,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Newspaper> newspaperPage = newspaperService.getAllByPublishingHouseId(page, pageSize, sortBy, id);
        PageDto pageDto = newspaperMapper.toDtoPage(newspaperPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Найти газеты по title")
    @GetMapping("/show-newspaper-by-title/{title}")
    private ResponseEntity<NewspaperDto> getByTittle(@PathVariable("title") String title) {
        Newspaper newspaper = newspaperService.getByTitle(title);
        NewspaperDto newspaperDto = newspaperMapper.toDto(newspaper);

        return new ResponseEntity<>(newspaperDto, HttpStatus.OK);
    }

    @ApiOperation("Найти газеты по id")
    @GetMapping("/{id}")
    private ResponseEntity<NewspaperDto> getById(@PathVariable("id") Long id) {
        Newspaper newspaper = newspaperService.getById(id);
        NewspaperDto newspaperDto = newspaperMapper.toDto(newspaper);

        return new ResponseEntity<>(newspaperDto, HttpStatus.OK);
    }

    @ApiOperation("Удалить газету по id")
    @PostMapping("/{id}")
    private ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        String response = newspaperService.delete(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Найти все газеты")
    @GetMapping()
    public ResponseEntity<PageDto> getAll
            (@RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "title") String sortBy) {
        Page<Newspaper> newspaperPage = newspaperService.getAll(page, pageSize, sortBy);
        PageDto pageDto = newspaperMapper.toDtoPage(newspaperPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить газету")
    @PostMapping()
    private ResponseEntity<NewspaperDto> create(@Valid @RequestBody NewspaperCreateDto newspaperCreateDto) {
        Newspaper newspaper = newspaperMapper.toEntityMethCreate(newspaperCreateDto);
        newspaper = newspaperService.save(newspaper);
        NewspaperDto dto = newspaperMapper.toDto(newspaper);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation("Изменить данные газеты")
    @PutMapping("/{id}")
    ResponseEntity<NewspaperDto> update(@PathVariable("id") Long id,
                                       @Valid @RequestBody NewspaperCreateDto dto) {
        Newspaper newspaper = newspaperMapper.toEntityMethCreate(dto);
        Newspaper newNewspaper = newspaperService.update(newspaper, id);
        NewspaperDto newDto = newspaperMapper.toDto(newNewspaper);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }

    @ApiOperation("Найти все газеты по номеру выпуска")
    @GetMapping("/show-newspapers-by-issue-number/{issueNumber}")
    public ResponseEntity<PageDto> getAllByIssueNumber
            (@PathVariable("issueNumber") Integer issueNumber,
             @RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Newspaper> newspaperPage = newspaperService.getAllByIssueNumber(page, pageSize, sortBy, issueNumber);
        PageDto pageDto = newspaperMapper.toDtoPage(newspaperPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}
