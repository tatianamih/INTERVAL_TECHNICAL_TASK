package by.interval.library.repository.impl;


import by.interval.library.model.models.Genre;
import by.interval.library.repository.GenreRepository;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.interval.library.repository.constants.GenreSQLQuery.*;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SELECT_FROM_GENRES,
                new BeanPropertyRowMapper<>(Genre.class));
    }

    @Override
    public Genre findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_GENRES_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(Genre.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("genre not found by id = ", id));
    }

    @Override
    public Genre save(Genre genre) {
        jdbcTemplate.update(INSERT_INTO_GENRES, genre.getName());
        return genre;
    }

    @Override
    public Genre update(Genre genre,Long id) {
        jdbcTemplate.update(UPDATE_GENRES,genre.getName(),id);
        return genre;
    }
}
