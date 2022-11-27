package by.interval.library.controller.controllers;



import by.interval.library.controller.dto.CityCreateDto;
import by.interval.library.controller.dto.CityDto;
import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.mappers.impl.CityMapper;
import by.interval.library.model.models.City;
import by.interval.library.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/cities")
@Log4j2
@RestController
@Api(tags = "City")
public class CityController {
    private final CityService cityService;

    private final CityMapper cityMapper;

    @Autowired
    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @ApiOperation("Найти город по id")
    @GetMapping("/{id}")
    private ResponseEntity<CityDto> getById(@PathVariable("id") Long id) {
        City city = cityService.getById(id);
        CityDto cityDto = cityMapper.toDto(city);

        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить город")
    @PostMapping()
    private ResponseEntity<CityDto> create(@Valid @RequestBody CityCreateDto cityDto) {
        City city = cityMapper.toEntityMethCreate(cityDto);
        city = cityService.save(city);
        CityDto dto = cityMapper.toDto(city);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation("Найти все города")
    @GetMapping()
    public ResponseEntity<PageDto> getAll
            (@RequestParam Integer page,
             @RequestParam Integer pageSize,
             @RequestParam(defaultValue = "name") String sortBy) {
        Page<City> cityPage = cityService.getAll(page, pageSize, sortBy);
        PageDto pageDto = cityMapper.toDtoPage(cityPage);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @ApiOperation("Изменить данные города")
    @PutMapping("/{id}")
    ResponseEntity<CityDto> update(@PathVariable("id") Long id,
                                   @Valid @RequestBody CityCreateDto dto) {
        City city = cityMapper.toEntityMethCreate(dto);
        City newCity = cityService.update(city, id);
        CityDto newDto = cityMapper.toDto(newCity);

        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }
}
