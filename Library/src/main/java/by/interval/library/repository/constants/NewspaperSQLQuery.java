package by.interval.library.repository.constants;

public class NewspaperSQLQuery {
    public static final String INSERT_INTO_NEWSPAPERS = "INSERT INTO Newspapers (title,issue_number,page_number," +
            "publication_year,printed_edition_number, author_id, publishing_house_id)" +
            " VALUES (:title,:issueNumber :pageNumber,:publicationYear,:printedEditionNumber," +
            ":authorId,:publishingHouseId)";
    public static final String SELECT_FROM_NEWSPAPERS_BY_ID = "SELECT * FROM Newspapers WHERE id = ?";
    public static final String UPDATE_NEWSPAPERS = "UPDATE Newspapers SET title = ?,issue_number = ?," +
            " page_number = ?, publication_year = ?, printed_edition_number = ?," +
            " author_id = ?, publishing_house_id = ? WHERE id = ?";
    public static final String SELECT_FROM_NEWSPAPERS = "SELECT * FROM Newspapers";
    public static final String SELECT_FROM_NEWSPAPERS_BY_AUTHOR_ID = "SELECT * FROM Newspapers WHERE author_id = ?";
    public static final String SELECT_FROM_NEWSPAPERS_BY_ISSUE_NUMBER = "SELECT * FROM Newspapers WHERE issue_number = ?";
    public static final String SELECT_FROM_NEWSPAPERS_BY_TITLE = "SELECT * FROM Newspapers WHERE title = ?";
    public static final String SELECT_FROM_NEWSPAPERS_BY_PUBLICATION_YEAR = "SELECT * FROM Newspapers WHERE" +
                                                                            " publication_year = ?";
    public static final String SELECT_FROM_NEWSPAPERS_BY_PUBLISHING_HOUSE_ID = "SELECT * FROM Newspapers WHERE" +
                                                                               " publishing_house_id = ?";
    public static final String DELETED_FROM_NEWSPAPERS_BY_ID = "DELETE FROM Newspapers WHERE id = ?";
}
