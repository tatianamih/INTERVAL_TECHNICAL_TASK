package by.interval.library.repository.impl;


import by.interval.library.model.models.Newspaper;
import by.interval.library.repository.NewspaperRepository;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.interval.library.repository.constants.NewspaperSQLQuery.*;

@Repository
public class NewspaperRepositoryImpl implements NewspaperRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewspaperRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Newspaper> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Newspaper> newspapers = jdbcTemplate.query(SELECT_FROM_NEWSPAPERS,
                new BeanPropertyRowMapper<>(Newspaper.class));
        return new PageImpl<>(newspapers, pageable, newspapers.size());
    }

    @Override
    public Page<Newspaper> findAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Newspaper> newspapers = jdbcTemplate.query(SELECT_FROM_NEWSPAPERS_BY_AUTHOR_ID, new Object[]{authorId},
                new BeanPropertyRowMapper<>(Newspaper.class));
        return new PageImpl<>(newspapers, pageable, newspapers.size());
    }

    @Override
    public Page<Newspaper> findAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Newspaper> newspapers = jdbcTemplate.query(SELECT_FROM_NEWSPAPERS_BY_ISSUE_NUMBER,
                new Object[]{issueNumber},
                new BeanPropertyRowMapper<>(Newspaper.class));
        return new PageImpl<>(newspapers, pageable, newspapers.size());
    }

    @Override
    public Newspaper findByTitle(String title) {
        return jdbcTemplate.query(SELECT_FROM_NEWSPAPERS_BY_TITLE, new Object[]{title},
                        new BeanPropertyRowMapper<>(Newspaper.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("newspapers not found by title = ", title));
    }

    @Override
    public Page<Newspaper> findAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Newspaper> newspapers = jdbcTemplate.query(SELECT_FROM_NEWSPAPERS_BY_PUBLICATION_YEAR,
                new Object[]{publicationYear},
                new BeanPropertyRowMapper<>(Newspaper.class));
        return new PageImpl<>(newspapers, pageable, newspapers.size());
    }

    @Override
    public Page<Newspaper> findAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Newspaper> newspapers = jdbcTemplate.query(SELECT_FROM_NEWSPAPERS_BY_PUBLISHING_HOUSE_ID,
                new Object[]{publishingHouseId},
                new BeanPropertyRowMapper<>(Newspaper.class));
        return new PageImpl<>(newspapers, pageable, newspapers.size());
    }

    @Override
    public Newspaper findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_NEWSPAPERS_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(Newspaper.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("newspaper not found by id = ", id));
    }

    @Override
    public Newspaper save(Newspaper newspaper) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", newspaper.getTitle());
        params.put("issueNumber", newspaper.getIssueNumber());
        params.put("pageNumber", newspaper.getPageNumber());
        params.put("publicationYear", newspaper.getPublicationYear());
        params.put("printedEditionNumber", newspaper.getPrintedEditionNumber());
        params.put("authorId", newspaper.getAuthorId());
        params.put("publishingHouseId", newspaper.getPublishingHouseId());
        namedParameterJdbcTemplate.update(INSERT_INTO_NEWSPAPERS, params);
        return newspaper;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETED_FROM_NEWSPAPERS_BY_ID, id);
    }

    @Override
    public Newspaper update(Newspaper newspaper, Long id) {
        jdbcTemplate.update(UPDATE_NEWSPAPERS,
                newspaper.getTitle(), newspaper.getIssueNumber(), newspaper.getPageNumber(),
                newspaper.getPublicationYear(), newspaper.getPrintedEditionNumber(),
                newspaper.getAuthorId(), newspaper.getPublishingHouseId(), id);
        return newspaper;
    }
}
