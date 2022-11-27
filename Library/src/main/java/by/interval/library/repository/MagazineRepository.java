package by.interval.library.repository;


import by.interval.library.model.models.Magazine;
import org.springframework.data.domain.Page;

public interface MagazineRepository {
    Page<Magazine> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Magazine> findAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId);

    Page<Magazine> findAllByGenreId(Integer pageNo, Integer pageSize, String sortBy, Long genreId);

    Page<Magazine> findAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber);

    Magazine findByTitle(String name);

    Page<Magazine> findAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear);

    Page<Magazine> findAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId);

    Magazine findById(Long id);

    Magazine save(Magazine magazine);

    void delete(Long id);

    Magazine update(Magazine magazine, Long id);
}
