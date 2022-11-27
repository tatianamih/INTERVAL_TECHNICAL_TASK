package by.interval.library.repository;


import by.interval.library.model.models.Book;
import org.springframework.data.domain.Page;

public interface BookRepository {
    Page<Book> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Book> findAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId);

    Page<Book> findAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId);

    Book findByTittle(String name);

    Page<Book> findAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear);

    Page<Book> findAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId);

    Book findById(Long id);

    Book save(Book book);

    void delete(Long id);

    Book update(Book book, Long id);
}
