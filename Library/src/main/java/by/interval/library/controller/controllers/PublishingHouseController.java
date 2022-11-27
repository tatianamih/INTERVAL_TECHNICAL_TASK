package by.interval.library.controller.controllers;


import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.dto.PublishingHouseCreateDto;
import by.interval.library.controller.dto.PublishingHouseDto;
import by.interval.library.controller.mappers.impl.PublishingHouseMapper;
import by.interval.library.model.models.PublishingHouse;
import by.interval.library.service.PublishingHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/publishing-houses")
@Log4j2
@RestController
@Api(tags = "PublishingHouse")
public class PublishingHouseController {
    private final PublishingHouseService publishingHouseService;

    private final PublishingHouseMapper publishingHouseMapper;


    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService,
                                     PublishingHouseMapper publishingHouseMapper) {
        this.publishingHouseService = publishingHouseService;
        this.publishingHouseMapper = publishingHouseMapper;
    }

    @ApiOperation("Найти издательство по id")
    @GetMapping("/{id}")
    private ResponseEntity<PublishingHouseDto> getById(@PathVariable("id") Long id) {
        PublishingHouse publishingHouse = publishingHouseService.getById(id);
        PublishingHouseDto dto = publishingHouseMapper.toDto(publishingHouse);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить издательство")
    @PostMapping()
    private ResponseEntity<PublishingHouseDto> create(@Valid @RequestBody PublishingHouseCreateDto publishingHouseCreateDto) {
        PublishingHouse publishingHouse = publishingHouseMapper.toEntityMethCreate(publishingHouseCreateDto);
        publishingHouse = publishingHouseService.save(publishingHouse);
        PublishingHouseDto dto = publishingHouseMapper.toDto(publishingHouse);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation("Найти все издательства")
    @GetMapping()
    public ResponseEntity<PageDto> getAll
            (@RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "name") String sortBy) {
        Page<PublishingHouse> publishingHousePage = publishingHouseService.getAll(page, pageSize, sortBy);
        PageDto pageDto = publishingHouseMapper.toDtoPage(publishingHousePage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Изменить данные издательства")
    @PutMapping("/{id}")
    ResponseEntity<PublishingHouseDto> update(@PathVariable("id") Long id,
                                              @Valid @RequestBody PublishingHouseCreateDto dto) {
        PublishingHouse publishingHouse = publishingHouseMapper.toEntityMethCreate(dto);
        PublishingHouse newPublishingHouses = publishingHouseService.update(publishingHouse, id);
        PublishingHouseDto newDto = publishingHouseMapper.toDto(newPublishingHouses);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }
}
