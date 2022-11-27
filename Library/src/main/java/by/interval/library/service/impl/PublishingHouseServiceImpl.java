package by.interval.library.service.impl;

import by.interval.library.model.models.PublishingHouse;
import by.interval.library.repository.PublishingHouseRepository;
import by.interval.library.service.PublishingHouseService;
import by.interval.library.service.exeptions.ResourceNotFoundException;
import by.interval.library.service.exeptions.ResourceRepetitionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {
    private final PublishingHouseRepository publishingHouseRepository;

    @Autowired
    public PublishingHouseServiceImpl(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @Override
    public Page<PublishingHouse> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("get all publishing house");
        return publishingHouseRepository.findAll(pageNo, pageSize, sortBy);
    }

    @Override
    public PublishingHouse getById(Long id) {
        log.info("get a publishing house by id {}", id);
        return publishingHouseRepository.findById(id);
    }

    @Override
    public PublishingHouse save(PublishingHouse publishingHouses) {
        try {
            publishingHouseRepository.save(publishingHouses);
        }catch (DataAccessException e){
            log.error("publishing house already exists with name -- {}", publishingHouses.getName());
            throw new ResourceRepetitionException("publishing house already exists with name ", publishingHouses.getName());
            }
        log.info("publishing houses was saved");
        return publishingHouses;
    }

    @Override
    public PublishingHouse update(PublishingHouse publishingHouse, Long id) {

        try {
            publishingHouseRepository.update(publishingHouse, id);
        }catch (DataAccessException e){
            log.error("check dates");
            throw new ResourceNotFoundException("check dates");
        }
        log.info("publishing house was updated");
        return publishingHouse;
    }
}
