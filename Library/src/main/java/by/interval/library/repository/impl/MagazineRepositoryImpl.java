package by.interval.library.repository.impl;


import by.interval.library.model.models.Magazine;
import by.interval.library.repository.MagazineRepository;
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

import static by.interval.library.repository.constants.MagazineSQLQuery.*;

@Repository
public class MagazineRepositoryImpl implements MagazineRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MagazineRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Magazine> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Magazine> magazines = jdbcTemplate.query(SELECT_FROM_MAGAZINES,
                new BeanPropertyRowMapper<>(Magazine.class));
        return new PageImpl<>(magazines, pageable, magazines.size());
    }

    @Override
    public Page<Magazine> findAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Magazine> magazines = jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_AUTHOR_ID, new Object[]{authorId},
                new BeanPropertyRowMapper<>(Magazine.class));
        return new PageImpl<>(magazines, pageable, magazines.size());
    }

    @Override
    public Page<Magazine> findAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Magazine> magazines = jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_GENRE_ID, new Object[]{genreId},
                new BeanPropertyRowMapper<>(Magazine.class));
        return new PageImpl<>(magazines, pageable, magazines.size());
    }

    @Override
    public Page<Magazine> findAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Magazine> magazines = jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_ISSUE_NUMBER,
                new Object[]{issueNumber},
                new BeanPropertyRowMapper<>(Magazine.class));
        return new PageImpl<>(magazines, pageable, magazines.size());
    }

    @Override
    public Magazine findByTitle(String title) {
        return jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_TITLE, new Object[]{title},
                        new BeanPropertyRowMapper<>(Magazine.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("magazines not found by title = ", title));
    }

    @Override
    public Page<Magazine> findAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Magazine> magazines = jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_PUBLICATION_YEAR,
                new Object[]{publicationYear},
                new BeanPropertyRowMapper<>(Magazine.class));
        return new PageImpl<>(magazines, pageable, magazines.size());
    }

    @Override
    public Page<Magazine> findAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Magazine> magazines = jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_PUBLISHING_HOUSE_ID,
                new Object[]{publishingHouseId},
                new BeanPropertyRowMapper<>(Magazine.class));
        return new PageImpl<>(magazines, pageable, magazines.size());
    }

    @Override
    public Magazine findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_MAGAZINES_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(Magazine.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("magazine not found by id = ", id));
    }

    @Override
    public Magazine save(Magazine magazine) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", magazine.getTitle());
        params.put("issueNumber", magazine.getIssueNumber());
        params.put("pageNumber", magazine.getPageNumber());
        params.put("publicationYear", magazine.getPublicationYear());
        params.put("printedEditionNumber", magazine.getPrintedEditionNumber());
        params.put("genreId", magazine.getGenreId());
        params.put("authorId", magazine.getAuthorId());
        params.put("publishingHouseId", magazine.getPublishingHouseId());
        namedParameterJdbcTemplate.update(INSERT_INTO_MAGAZINES,params);
        return magazine;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETED_FROM_MAGAZINES_BY_ID, id);
    }

    @Override
    public Magazine update(Magazine magazine, Long id) {
        jdbcTemplate.update(UPDATE_MAGAZINES,
                magazine.getTitle(), magazine.getIssueNumber(), magazine.getPageNumber(),
                magazine.getPublicationYear(), magazine.getPrintedEditionNumber(),
                magazine.getAuthorId(), magazine.getPublishingHouseId(), magazine.getGenreId(), id);
        return magazine;
    }
}
