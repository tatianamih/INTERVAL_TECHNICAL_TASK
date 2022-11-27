package by.interval.library.service.impl;

import by.interval.library.model.models.Newspaper;
import by.interval.library.repository.NewspaperRepository;
import by.interval.library.service.NewspaperService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NewspaperServiceImpl implements NewspaperService {
    private final NewspaperRepository newspaperRepository;

    @Autowired
    public NewspaperServiceImpl(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    @Override
    public Page<Newspaper> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("get all newspaper");
        return newspaperRepository.findAll(pageNo, pageSize, sortBy);
    }

    @Override
    public Page<Newspaper> getAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber) {
        Page<Newspaper> newspaperPage;
        try {
            newspaperPage = newspaperRepository.findAllByIssueNumber(pageNo, pageSize, sortBy, issueNumber);
        } catch (DataAccessException e) {
            log.error("newspapers not found by issue number  -- {}", issueNumber);
            throw new ResourceNotFoundException("newspapers not found by issue number ", issueNumber);
        }
        log.info("get all newspapers by issue number id");
        return newspaperPage;
    }

    @Override
    public Page<Newspaper> getAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId) {
        Page<Newspaper> newspaperPage;
        try {
            newspaperPage = newspaperRepository.findAllByAuthorId(pageNo, pageSize, sortBy, authorId);
        } catch (DataAccessException e) {
            log.error("newspapers not found by author id -- {}", authorId);
            throw new ResourceNotFoundException("newspapers not found by author id  ", authorId);
        }
        log.info("get all newspapers by author id");
        return newspaperPage;
    }

    @Override
    public Newspaper getByTitle(String title) {
        log.info("get newspaper by title");
        return newspaperRepository.findByTitle(title);
    }

    @Override
    public Page<Newspaper> getAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear) {
        Page<Newspaper> newspaperPage;
        try {
            newspaperPage = newspaperRepository.findAllByPublicationYear(pageNo, pageSize, sortBy, publicationYear);
        } catch (DataAccessException e) {
            log.error("newspapers not found by publishing year -- {}", publicationYear);
            throw new ResourceNotFoundException("newspapers not found by publishing year", publicationYear);
        }
        log.info("get all newspapers by publishing year");
        return newspaperPage;
    }

    @Override
    public Page<Newspaper> getAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId) {
        Page<Newspaper> newspaperPage;
        try {
            newspaperPage = newspaperRepository.findAllByPublishingHouseId(pageNo, pageSize, sortBy, publishingHouseId);
        } catch (DataAccessException e) {
            log.error("newspapers not found by publishing house id -- {}", publishingHouseId);
            throw new ResourceNotFoundException("newspapers not found by publishing house id ", publishingHouseId);
        }
        log.info("get all newspapers by publishing house id");
        return newspaperPage;
    }

    @Override
    public Newspaper getById(Long id) {
        log.info("get a newspaper by id {}", id);
        return newspaperRepository.findById(id);
    }

    @Override
    public Newspaper save(Newspaper newspaper) {
        try {
            newspaperRepository.save(newspaper);
        } catch (DataAccessException e) {
            log.error("newspaper already exists with title -- {}", newspaper.getTitle());
            throw new ResourceRepetitionException("newspaper already exists with title ", newspaper.getTitle());
        }
        log.info("newspaper was saved");
        return newspaper;
    }

    @Override
    public String delete(Long id) {
        getById(id);
        log.info("newspaper was deleted");
        newspaperRepository.delete(id);
        return "newspaper by id " + id + " was deleted";
    }

    @Override
    public Newspaper update(Newspaper newspaper, Long id) {
        try {
            newspaperRepository.update(newspaper, id);
        } catch (DataAccessException e) {
            log.error("check data");
            throw new ResourceNotFoundException("check data");
        }
        log.info("newspaper was updated");
        return newspaper;
    }
}
