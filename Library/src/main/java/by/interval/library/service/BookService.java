package by.interval.library.service;

import by.interval.library.model.models.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    Page<Book> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Book> getAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId);

    Page<Book> getAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId);

    Book getByTittle(String name);

    Page<Book> getAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear);

    Page<Book> getAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId);

    Book getById(Long id);

    Book save(Book book);

    String delete(Long id);

    Book update(Book book, Long id);
}
