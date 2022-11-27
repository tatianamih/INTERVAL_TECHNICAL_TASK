package by.interval.library.repository.constants;

public class MagazineSQLQuery {
    public static final String INSERT_INTO_MAGAZINES = "INSERT INTO Magazines (title,issue_number,page_number," +
            "publication_year,printed_edition_number, genre_id, author_id, publishing_house_id)" +
            " VALUES (:title,:issueNumber :pageNumber,:publicationYear,:printedEditionNumber," +
            ":genreId,:authorId,:publishingHouseId)";
    public static final String SELECT_FROM_MAGAZINES_BY_ID = "SELECT * FROM Magazines WHERE id = ?";
    public static final String UPDATE_MAGAZINES = "UPDATE Magazines SET title = ?,issue_number = ?, page_number = ?, " +
            "publication_year = ?, printed_edition_number = ?, author_id = ?, publishing_house_id = ?," +
            " genre_id = ? WHERE id = ?";
    public static final String SELECT_FROM_MAGAZINES = "SELECT * FROM Magazines";
    public static final String SELECT_FROM_MAGAZINES_BY_AUTHOR_ID = "SELECT * FROM Magazines WHERE author_id = ?";
    public static final String SELECT_FROM_MAGAZINES_BY_GENRE_ID = "SELECT * FROM Magazines WHERE genre_id = ?";
    public static final String SELECT_FROM_MAGAZINES_BY_ISSUE_NUMBER = "SELECT * FROM Magazines WHERE issue_number = ?";
    public static final String SELECT_FROM_MAGAZINES_BY_TITLE = "SELECT * FROM Magazines WHERE title = ?";
    public static final String SELECT_FROM_MAGAZINES_BY_PUBLICATION_YEAR = "SELECT * FROM Magazines WHERE publication_year = ?";
    public static final String SELECT_FROM_MAGAZINES_BY_PUBLISHING_HOUSE_ID = "SELECT * FROM Magazines WHERE publishing_house_id = ?";
    public static final String DELETED_FROM_MAGAZINES_BY_ID = "DELETE FROM Magazines WHERE id = ?";
}
