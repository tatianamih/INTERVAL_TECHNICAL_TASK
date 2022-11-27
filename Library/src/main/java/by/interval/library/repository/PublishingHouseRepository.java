package by.interval.library.repository;


import by.interval.library.model.models.PublishingHouse;
import org.springframework.data.domain.Page;


public interface PublishingHouseRepository {
    Page<PublishingHouse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    PublishingHouse findById(Long id);

    PublishingHouse save(PublishingHouse publishingHouse);

    PublishingHouse update(PublishingHouse publishingHouse, Long id);
}
