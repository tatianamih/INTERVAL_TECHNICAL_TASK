package by.interval.library.service;

import by.interval.library.model.models.Author;
import org.springframework.data.domain.Page;

public interface AuthorService {
    Page<Author> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Author getById(Long id);

    Author save(Author author);

    Author update(Author author, Long id);
}
