package by.interval.library.repository.constants;

public class BookSQLQuery {
    public static final String INSERT_INTO_BOOKS = "INSERT INTO Books (title,page_number,publication_year," +
            "printed_edition_number,genre_id, author_id, publishing_house_id)" +
            " VALUES (:title, :pageNumber,:publicationYear,:printedEditionNumber,:genreId,:authorId,:publishingHouseId)";
    public static final String SELECT_FROM_BOOKS_BY_ID = "SELECT * FROM Books WHERE id = ?";
    public static final String UPDATE_BOOKS = "UPDATE Books SET title = ?, page_number = ?, publication_year = ?," +
            " printed_edition_number = ?, author_id = ?, publishing_house_id = ?, genre_id = ? WHERE id = ?";
    public static final String SELECT_FROM_BOOKS = "SELECT * FROM Books";
    public static final String SELECT_FROM_BOOKS_BY_AUTHOR_ID = "SELECT * FROM Books WHERE author_id = ?";
    public static final String SELECT_FROM_BOOKS_BY_GENRE_ID = "SELECT * FROM Books WHERE genre_id = ?";
    public static final String SELECT_FROM_BOOKS_BY_TITLE = "SELECT * FROM Books WHERE title = ?";
    public static final String SELECT_FROM_BOOKS_BY_PUBLICATION_YEAR = "SELECT * FROM Books WHERE publication_year = ?";
    public static final String SELECT_FROM_BOOKS_BY_PUBLISHING_HOUSE_ID = "SELECT * FROM Books WHERE publishing_house_id = ?";
    public static final String DELETED_FROM_BOOKS_BY_ID = "DELETE FROM Books WHERE id = ?";
}
