package by.interval.library.controller.mappers.impl;


import by.interval.library.controller.dto.MagazineCreateDto;
import by.interval.library.controller.dto.MagazineDto;
import by.interval.library.controller.dto.PageDto;
import by.interval.library.controller.mappers.GenericMapper;
import by.interval.library.model.models.Magazine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for converting the Magazine entity to / from DTO
 */
@Component
public class MagazineMapper implements GenericMapper<Magazine, MagazineDto, MagazineCreateDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MagazineDto toDto(Magazine magazine) {
        return Objects.isNull(magazine) ? null : modelMapper.map(magazine,MagazineDto.class);
    }

    @Override
    public Magazine toEntity(MagazineDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Magazine.class);
    }

    @Override
    public Magazine toEntityMethCreate(MagazineCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Magazine.class);
    }

    @Override
    public List<MagazineDto> toDtoList(List<Magazine> entityList) {
        return entityList.stream()
                .map(magazine-> modelMapper.map(magazine, MagazineDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Magazine> toEntityList(List<MagazineDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, Magazine.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageDto toDtoPage(Page<Magazine> entitiesPage) {
        PageDto pageDto = new PageDto();
        pageDto.setList(entitiesPage.getContent()
                .stream().map(magazine -> modelMapper.map(magazine, MagazineDto.class))
                .collect(Collectors.toCollection(ArrayList::new)));
        pageDto.setTotalPages(entitiesPage.getTotalPages());
        return pageDto;
    }
}
