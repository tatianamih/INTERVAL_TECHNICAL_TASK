package by.interval.library.controller.mappers.impl;


import by.interval.library.controller.dto.*;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.Author;
import by.interval.library.model.models.PublishingHouse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the PublishingHouse entity to / from DTO
 */
@Component
public class PublishingHouseMapper implements GenericMapper<PublishingHouse, PublishingHouseDto,PublishingHouseCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PublishingHouseDto toDto(PublishingHouse publishingHouse) {
        return Objects.isNull(publishingHouse) ? null : modelMapper.map(publishingHouse,PublishingHouseDto.class);
    }

    @Override
    public PublishingHouse toEntity(PublishingHouseDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, PublishingHouse.class);
    }

    @Override
    public PublishingHouse toEntityMethCreate(PublishingHouseCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, PublishingHouse.class);
    }

    @Override
    public List<PublishingHouseDto> toDtoList(List<PublishingHouse> entityList) {
        return entityList.stream()
                .map(publishingHouse -> modelMapper.map(publishingHouse, PublishingHouseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PublishingHouse> toEntityList(List<PublishingHouseDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, PublishingHouse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<PublishingHouse> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(publishingHouse -> modelMapper.map(publishingHouse, PublishingHouseDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
