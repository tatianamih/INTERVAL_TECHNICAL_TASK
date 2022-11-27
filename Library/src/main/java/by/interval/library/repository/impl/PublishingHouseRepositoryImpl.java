package by.interval.library.repository.impl;


import by.interval.library.model.models.PublishingHouse;
import by.interval.library.repository.PublishingHouseRepository;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.interval.library.repository.constants.PublishingHouseSQLQuery.*;

@Repository
public class PublishingHouseRepositoryImpl implements PublishingHouseRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PublishingHouseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<PublishingHouse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<PublishingHouse> publishingHouses = jdbcTemplate.query(SELECT_FROM_PUBLISHING_HOUSES,
                new BeanPropertyRowMapper<>(PublishingHouse.class));
        return new PageImpl<>(publishingHouses, pageable, publishingHouses.size());
    }

    @Override
    public PublishingHouse findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_PUBLISHING_HOUSES_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(PublishingHouse.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("publishing houses not found by id = ", id));
    }

    @Transactional
    @Override
    public PublishingHouse save(PublishingHouse publishingHouse) {
        jdbcTemplate.update(INSERT_INTO_PUBLISHING_HOUSES, publishingHouse.getName(), publishingHouse.getCityId());
        return publishingHouse;
    }

    @Override
    public PublishingHouse update(PublishingHouse publishingHouse, Long id) {
        jdbcTemplate.update(UPDATE_PUBLISHING_HOUSES, publishingHouse.getName(), publishingHouse.getCityId(), id);
        return publishingHouse;
    }
}
