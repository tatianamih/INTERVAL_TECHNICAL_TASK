package by.interval.library.service;

import by.interval.library.model.models.Magazine;
import org.springframework.data.domain.Page;

public interface MagazineService {
    Page<Magazine> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Magazine> getAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber);

    Page<Magazine> getAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId);

    Page<Magazine> getAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId);

    Magazine getByTittle(String name);

    Page<Magazine> getAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear);

    Page<Magazine> getAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId);

    Magazine getById(Long id);

    Magazine save(Magazine magazine);

    String delete(Long id);

    Magazine update(Magazine magazine, Long id);
}
