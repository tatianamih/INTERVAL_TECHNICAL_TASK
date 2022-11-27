package by.interval.library.repository.impl;


import by.interval.library.model.models.Book;
import by.interval.library.repository.BookRepository;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.interval.library.repository.constants.BookSQLQuery.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Book> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Book> books = jdbcTemplate.query(SELECT_FROM_BOOKS,
                new BeanPropertyRowMapper<>(Book.class));
        return new PageImpl<>(books, pageable, books.size());
    }

    @Override
    public Page<Book> findAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Book> books = jdbcTemplate.query(SELECT_FROM_BOOKS_BY_AUTHOR_ID, new Object[]{authorId},
                new BeanPropertyRowMapper<>(Book.class));
        return new PageImpl<>(books, pageable, books.size());
    }

    @Override
    public Page<Book> findAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Book> books = jdbcTemplate.query(SELECT_FROM_BOOKS_BY_GENRE_ID, new Object[]{genreId},
                new BeanPropertyRowMapper<>(Book.class));
        return new PageImpl<>(books, pageable, books.size());
    }

    @Override
    public Book findByTittle(String title) {
        return jdbcTemplate.query(SELECT_FROM_BOOKS_BY_TITLE, new Object[]{title},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("book not found by tittle = ", title));
    }

    @Override
    public Page<Book> findAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Book> books = jdbcTemplate.query(SELECT_FROM_BOOKS_BY_PUBLICATION_YEAR,
                new Object[]{publicationYear},
                new BeanPropertyRowMapper<>(Book.class));
        return new PageImpl<Book>(books, pageable, books.size());
    }

    @Override
    public Page<Book> findAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Book> books = jdbcTemplate.query(SELECT_FROM_BOOKS_BY_PUBLISHING_HOUSE_ID,
                new Object[]{publishingHouseId},
                new BeanPropertyRowMapper<>(Book.class));
        return new PageImpl<Book>(books, pageable, books.size());
    }


    @Override
    public Book findById(Long id) {
        return jdbcTemplate.query(SELECT_FROM_BOOKS_BY_ID, new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElseThrow(() ->
                        new ResourceNotFoundException("book not found by id = ", id));
    }

    @Override
    public Book save(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("pageNumber", book.getPageNumber());
        params.put("publicationYear", book.getPublicationYear());
        params.put("printedEditionNumber", book.getPrintedEditionNumber());
        params.put("genreId", book.getGenreId());
        params.put("authorId", book.getAuthorId());
        params.put("publishingHouseId", book.getPublishingHouseId());
        namedParameterJdbcTemplate.update(INSERT_INTO_BOOKS, params);
        return book;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETED_FROM_BOOKS_BY_ID, id);
    }

    @Override
    public Book update(Book book, Long id) {
        jdbcTemplate.update(UPDATE_BOOKS,
                book.getTitle(), book.getPageNumber(), book.getPublicationYear(),
                book.getPrintedEditionNumber(), book.getAuthorId(), book.getPublishingHouseId(), book.getGenreId(),id);
        return book;
    }
}
