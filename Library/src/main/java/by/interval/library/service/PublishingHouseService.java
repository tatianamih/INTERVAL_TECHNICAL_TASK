package by.interval.library.service;

import by.interval.library.model.models.PublishingHouse;
import org.springframework.data.domain.Page;

public interface PublishingHouseService {
    Page<PublishingHouse> getAll(Integer pageNo, Integer pageSize, String sortBy);

    PublishingHouse getById(Long id);

    PublishingHouse save(PublishingHouse publishingHouse);

    PublishingHouse update(PublishingHouse publishingHouse, Long id);
}
