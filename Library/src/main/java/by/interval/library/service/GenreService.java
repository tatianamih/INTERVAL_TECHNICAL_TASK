package by.interval.library.service;

import by.interval.library.model.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getById(Long id);

    Genre save(Genre genre);

    Genre update(Genre genre, Long id);
}
