package by.interval.library.service.impl;

import by.interval.library.model.models.Book;
import by.interval.library.repository.BookRepository;
import by.interval.library.service.BookService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("get all books");
        return bookRepository.findAll(pageNo, pageSize, sortBy);
    }

    @Override
    public Page<Book> getAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId) {
        Page<Book> bookPage;
        try {
           bookPage = bookRepository.findAllByAuthorId(pageNo, pageSize, sortBy, authorId);
        }catch (DataAccessException e){
            log.error("books not found by author id -- {}", authorId);
            throw new ResourceNotFoundException("books not found by author id  ", authorId);
        }
        log.info("get all books by author id");
      return bookPage;
    }

    @Override
    public Page<Book> getAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId) {
        Page<Book> bookPage;
        try {
            bookPage = bookRepository.findAllByGenreId(pageNo, pageSize, sortBy, genreId);
        }catch (DataAccessException e){
            log.error("books not found by genre id -- {}", genreId);
            throw new ResourceNotFoundException("books not found by genre id  ", genreId);
        }
        log.info("get all books by genre id");
        return bookPage;
    }

    @Override
    public Book getByTittle(String tittle) {
        log.info("get book by tittle");
        return bookRepository.findByTittle(tittle);
    }

    @Override
    public Page<Book> getAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear) {
        Page<Book> bookPage;
        try {
            bookPage = bookRepository.findAllByPublicationYear(pageNo, pageSize, sortBy, publicationYear);
        }catch (DataAccessException e){
            log.error("books not found by publishing year -- {}", publicationYear);
            throw new ResourceNotFoundException("books not found by publishing year", publicationYear);
        }
        log.info("get all books by publishing year");
        return bookPage;
    }

    @Override
    public Page<Book> getAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId) {
        Page<Book> bookPage;
        try {
            bookPage = bookRepository.findAllByPublishingHouseId(pageNo, pageSize, sortBy, publishingHouseId);
        }catch (DataAccessException e){
            log.error("books not found by publishing house id -- {}", publishingHouseId);
            throw new ResourceNotFoundException("books not found by publishing house id ", publishingHouseId);
        }
        log.info("get all books by publishing house id");
        return bookPage;
    }

    @Override
    public Book getById(Long id) {
        log.info("get a book by id {}", id);
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        try {
            bookRepository.save(book);
        }catch (DataAccessException e){
            log.error("book already exists with title -- {}", book.getTitle());
            throw new ResourceRepetitionException("book already exists with title ", book.getTitle());
            }
        log.info("book was saved");
        return book;
    }

    @Override
    public String delete(Long id) {
        getById(id);
        log.info("book was deleted");
        bookRepository.delete(id);
        return "book by id " + id + " was deleted";
    }

    @Override
    public Book update(Book book, Long id) {
        try {
            bookRepository.update(book, id);
        }catch (DataAccessException e){
            log.error("check data");
            throw new ResourceNotFoundException("check data");
        }
        log.info("book was updated");
        return book;
    }
}
