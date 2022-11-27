package by.interval.library.repository;


import by.interval.library.model.models.Newspaper;
import org.springframework.data.domain.Page;

public interface NewspaperRepository {
    Page<Newspaper> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Newspaper> findAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId);

    Page<Newspaper> findAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber);

    Newspaper findByTitle(String title);

    Page<Newspaper> findAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear);

    Page<Newspaper> findAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId);

    Newspaper findById(Long id);

    Newspaper save(Newspaper newspaper);

    void delete(Long id);

    Newspaper update(Newspaper newspaper, Long id);
}
