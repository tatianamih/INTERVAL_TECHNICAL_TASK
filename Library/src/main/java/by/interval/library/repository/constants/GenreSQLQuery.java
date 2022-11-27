package by.interval.library.repository.constants;

public class GenreSQLQuery {
    public static final String INSERT_INTO_GENRES = "INSERT INTO Genres (name) VALUES(?)";
    public static final String SELECT_FROM_GENRES_BY_ID = "SELECT * FROM Genres WHERE id = ?";
    public static final String UPDATE_GENRES = "UPDATE Genres SET  name = ?  WHERE id = ?";
    public static final String SELECT_FROM_GENRES = "SELECT * FROM Genres";
}
