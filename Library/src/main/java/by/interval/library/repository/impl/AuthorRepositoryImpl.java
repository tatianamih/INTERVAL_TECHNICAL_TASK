package by.interval.library.repository.impl;


import by.interval.library.model.models.Author;
import by.interval.library.repository.AuthorRepository;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.interval.library.repository.constants.AuthorSQLQuery.*;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Author> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Author> authors = jdbcTemplate.query(SELECT_FROM_AUTHORS,
                new BeanPropertyRowMapper<>(Author.class));
        return new PageImpl<>(authors, pageable, authors.size());
    }

    @Override
    public Author findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_AUTHORS_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(Author.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("authors not found by id = ", id));
    }

    @Override
    public Author save(Author author) {
        jdbcTemplate.update(INSERT_INTO_AUTHORS, author.getName());
        return author;
    }

    @Override
    public Author update(Author author, Long id) {
        jdbcTemplate.update(UPDATE_AUTHORS, author.getName(), id);
        return author;
    }
}
