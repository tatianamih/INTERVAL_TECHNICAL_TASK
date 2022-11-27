package by.interval.library.service;

import by.interval.library.model.models.City;
import org.springframework.data.domain.Page;

public interface CityService {
    Page<City> getAll(Integer pageNo, Integer pageSize, String sortBy);;

    City getById(Long id);

    City save(City city);

    City update(City city, Long id);
}
