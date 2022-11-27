package by.interval.library.repository.impl;


import by.interval.library.model.models.City;
import by.interval.library.repository.CityRepository;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.interval.library.repository.constants.CitySQLQuery.*;

@Repository
public class CityRepositoryImpl implements CityRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<City> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<City> cities = jdbcTemplate.query(SELECT_FROM_CITIES,
                new BeanPropertyRowMapper<>(City.class));
        return new PageImpl<>(cities, pageable, cities.size());
    }

    @Override
    public City findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_CITIES_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(City.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("city not found by id = ", id));
    }

    @Override
    public City save(City city) {
        jdbcTemplate.update(INSERT_INTO_CITIES, city.getName());
        return city;
    }

    @Override
    public City update(City city, Long id) {
        jdbcTemplate.update(UPDATE_CITIES, city.getName(), id);
        return city;
    }
}
