package by.interval.library.repository;


import by.interval.library.model.models.Genre;

import java.util.List;


public interface GenreRepository{
    List<Genre> findAll();

    Genre findById(Long id);

    Genre save(Genre genre);

    Genre update(Genre genre, Long id);
}

