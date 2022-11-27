package by.interval.library.repository.constants;

public class AuthorSQLQuery {
    public static final String INSERT_INTO_AUTHORS = "INSERT INTO Authors (name) VALUES(?)";
    public static final String SELECT_FROM_AUTHORS_BY_ID = "SELECT * FROM Authors WHERE id=?";
    public static final String UPDATE_AUTHORS = "UPDATE Authors SET  name = ?  WHERE id = ?";
    public static final String SELECT_FROM_AUTHORS = "SELECT * FROM Authors";
}
