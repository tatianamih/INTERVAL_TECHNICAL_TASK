package by.interval.library.service.impl;

import by.interval.library.model.models.City;
import by.interval.library.model.models.Genre;
import by.interval.library.repository.GenreRepository;
import by.interval.library.service.GenreService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
      }

    @Override
    public List<Genre> getAll() {
        log.info("get all genres");
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(Long id) {
        log.info("get a genre by id {}", id);
        return genreRepository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        try {
            genreRepository.save(genre);
        }catch (DataAccessException e){
            log.error("genre already exists with name -- {}", genre.getName());
            throw new ResourceRepetitionException("genre already exists with name ", genre.getName());
        }
        log.info("genre was saved");
        return genre;
    }
    @Override
    public Genre update(Genre genre, Long id) {
        try {
            genreRepository.update(genre, id);
        }catch (DataAccessException e){
            log.error("genre already exists with name -- {}", genre.getName());
            throw new ResourceNotFoundException("genre don't exist with id ", id);
        }
        log.info("genre was updated");
        return genre;
    }
}

//добавить изменить