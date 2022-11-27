package by.interval.library.service.impl;

import by.interval.library.model.models.City;
import by.interval.library.repository.CityRepository;
import by.interval.library.service.CityService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;


    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Page<City> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("get all cities");
        return cityRepository.findAll(pageNo,pageSize, sortBy);
    }

    @Override
    public City getById(Long id) {
        log.info("get a city by id {}", id);
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city){
        try {
            cityRepository.save(city);
        }catch (DataAccessException e){
            log.error("city already exists with name -- {}", city.getName());
            throw new ResourceRepetitionException("city already exists with name ", city.getName());
        }
        log.info("city was saved");
        return city;
    }

    @Override
    public City update(City city,Long id) {
        try {
            cityRepository.update(city, id);
        }catch (DataAccessException e){
            log.error("city already exists with name -- {}", city.getName());
            throw new ResourceNotFoundException("city don't exist with id ", id);
        }
        log.info("city was updated");
        return city;
    }
}

