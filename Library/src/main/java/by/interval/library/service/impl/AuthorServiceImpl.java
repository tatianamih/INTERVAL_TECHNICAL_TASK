package by.interval.library.service.impl;

import by.interval.library.model.models.Author;
import by.interval.library.repository.AuthorRepository;
import by.interval.library.service.AuthorService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<Author> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("get all authors");
        return authorRepository.findAll(pageNo, pageSize, sortBy);
    }

    @Override
    public Author getById(Long id) {
        log.info("get an author by id {}", id);
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        try {
            authorRepository.save(author);
        }catch (DataAccessException e){
            log.error("author already exists with name -- {}", author.getName());
            throw new ResourceRepetitionException("author already exists with name ", author.getName());
            }
        log.info("author was saved");
        return author;
    }

    @Override
    public Author update(Author author, Long id) {
        try {
            authorRepository.update(author, id);
        }catch (DataAccessException e){
            log.error("author already exists with name -- {}", author.getName());
            throw new ResourceNotFoundException("author don't exist with id ", id);
        }
        log.info("author was updated");
        return author;
    }
}
