package by.interval.library.repository;


import by.interval.library.model.models.Author;
import org.springframework.data.domain.Page;


public interface AuthorRepository {
    Page<Author> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Author findById(Long id);

    Author save(Author author);

    Author update(Author author, Long id);
}

