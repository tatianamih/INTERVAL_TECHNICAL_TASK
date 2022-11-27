package by.interval.library.service.impl;

import by.interval.library.model.models.Magazine;
import by.interval.library.repository.MagazineRepository;
import by.interval.library.service.MagazineService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MagazineServiceImpl implements MagazineService {
    private final MagazineRepository magazineRepository;

    @Autowired
    public MagazineServiceImpl( MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @Override
    public Page<Magazine> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("get all magazines");
        return magazineRepository.findAll(pageNo, pageSize, sortBy);
    }

    @Override
    public Page<Magazine> getAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber) {
        Page<Magazine> magazinePage;
        try{
            magazinePage = magazineRepository.findAllByIssueNumber(pageNo, pageSize, sortBy, issueNumber);
        } catch (DataAccessException e){
                    log.error("magazines not found by issue number  -- {}", issueNumber);
                    throw new ResourceNotFoundException("magazines not found by issue number ", issueNumber);
        }
        log.info("get all magazines by issue number id");
        return magazinePage;
    }

    @Override
    public Page<Magazine> getAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId) {
        Page<Magazine> magazinePage;
        try {
           magazinePage = magazineRepository.findAllByAuthorId(pageNo, pageSize, sortBy, authorId);
        }catch (DataAccessException e){
            log.error("magazines not found by author id -- {}", authorId);
            throw new ResourceNotFoundException("magazines not found by author id  ", authorId);
        }
        log.info("get all magazines by author id");
      return magazinePage;
    }

    @Override
    public Page< Magazine> getAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId) {
        Page< Magazine> magazinePage;
        try {
            magazinePage = magazineRepository.findAllByGenreId(pageNo, pageSize, sortBy, genreId);
        }catch (DataAccessException e){
            log.error("magazines not found by genre id -- {}", genreId);
            throw new ResourceNotFoundException("magazines not found by genre id  ", genreId);
        }
        log.info("get all magazines by genre id");
        return magazinePage;
    }

    @Override
    public  Magazine getByTittle(String title) {
        log.info("get magazine by title");
        return magazineRepository.findByTitle(title);
    }

    @Override
    public Page< Magazine> getAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear) {
        Page< Magazine> magazinePage;
        try {
            magazinePage = magazineRepository.findAllByPublicationYear(pageNo, pageSize, sortBy, publicationYear);
        }catch (DataAccessException e){
            log.error("magazines not found by publishing year -- {}", publicationYear);
            throw new ResourceNotFoundException("books not found by publishing year", publicationYear);
        }
        log.info("get all magazines by publishing year");
        return magazinePage;
    }

    @Override
    public Page<Magazine> getAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId) {
        Page< Magazine> magazinePage;
        try {
            magazinePage = magazineRepository.findAllByPublishingHouseId(pageNo, pageSize, sortBy, publishingHouseId);
        }catch (DataAccessException e){
            log.error("magazines not found by publishing house id -- {}", publishingHouseId);
            throw new ResourceNotFoundException("magazines not found by publishing house id ", publishingHouseId);
        }
        log.info("get all magazines by publishing house id");
        return magazinePage;
    }

    @Override
    public  Magazine getById(Long id) {
        log.info("get a magazine by id {}", id);
        return magazineRepository.findById(id);
    }

    @Override
    public Magazine save(Magazine magazine) {
        try {
            magazineRepository.save(magazine);
        }catch (DataAccessException e){
            log.error("magazine already exists with title -- {}", magazine.getTitle());
            throw new ResourceRepetitionException("magazine already exists with title ", magazine.getTitle());
            }
        log.info("magazine was saved");
        return magazine;
    }

    @Override
    public String delete(Long id) {
        getById(id);
        log.info("magazine was deleted");
        magazineRepository.delete(id);
        return "magazine by id " + id + " was deleted";
    }

    @Override
    public Magazine update(Magazine magazine, Long id) {
        try {
            magazineRepository.update(magazine, id);
        }catch (DataAccessException e){
            log.error("check data");
            throw new ResourceNotFoundException("check data");
        }
        log.info("magazine was updated");
        return magazine;
    }
}
