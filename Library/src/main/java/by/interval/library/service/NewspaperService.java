package by.interval.library.service;

import by.interval.library.model.models.Newspaper;
import org.springframework.data.domain.Page;

public interface NewspaperService {
    Page<Newspaper> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Newspaper> getAllByIssueNumber(Integer pageNo, Integer pageSize, String sortBy, Integer issueNumber);

    Page<Newspaper> getAllByAuthorId(Integer pageNo, Integer pageSize, String sortBy, Long authorId);

    Newspaper getByTitle(String title);

    Page<Newspaper> getAllByPublicationYear(Integer pageNo, Integer pageSize, String sortBy, Integer publicationYear);

    Page<Newspaper> getAllByPublishingHouseId(Integer pageNo, Integer pageSize, String sortBy, Long publishingHouseId);

    Newspaper getById(Long id);

    Newspaper save(Newspaper newspaper);

    String delete(Long id);

    Newspaper update(Newspaper newspaper, Long id);
}
