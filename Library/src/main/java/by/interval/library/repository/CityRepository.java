package by.interval.library.repository;


import by.interval.library.model.models.City;
import org.springframework.data.domain.Page;


public interface CityRepository {
    Page<City> findAll(Integer pageNo, Integer pageSize, String sortBy);

    City findById(Long id);

    City save(City city);

    City update(City city, Long id);
}

