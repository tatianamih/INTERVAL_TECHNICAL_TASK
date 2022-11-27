package by.interval.library.controller.mappers.impl;



import by.interval.library.controller.dto.CityCreateDto;
import by.interval.library.controller.dto.CityDto;
import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the City entity to / from DTO
 */
@Component
public class CityMapper implements GenericMapper<City, CityDto, CityCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDto toDto(City city) {
        return Objects.isNull(city) ? null : modelMapper.map(city, CityDto.class);
    }

    @Override
    public City toEntity(CityDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, City.class);
    }

    @Override
    public City toEntityMethCreate(CityCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, City.class);
    }

    @Override
    public List<CityDto> toDtoList(List<City> entityList) {
        return entityList.stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<City> toEntityList(List<CityDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, City.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<City> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
