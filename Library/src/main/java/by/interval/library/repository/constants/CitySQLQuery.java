package by.interval.library.repository.constants;

public class CitySQLQuery {
    public static final String INSERT_INTO_CITIES = "INSERT INTO Cities (name) VALUES(?)";
    public static final String SELECT_FROM_CITIES_BY_ID = "SELECT * FROM Cities WHERE id = ?";
    public static final String UPDATE_CITIES = "UPDATE Cities SET  name = ?  WHERE id = ?";
    public static final String SELECT_FROM_CITIES = "SELECT * FROM Cities";
}
