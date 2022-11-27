package by.interval.library.repository.constants;

public class PublishingHouseSQLQuery {
    public static final String INSERT_INTO_PUBLISHING_HOUSES = "INSERT INTO PUBLISHING_HOUSES (name,city_id) VALUES(?,?)";
    public static final String SELECT_FROM_PUBLISHING_HOUSES_BY_ID = "SELECT * FROM PUBLISHING_HOUSES WHERE id = ?";
    public static final String UPDATE_PUBLISHING_HOUSES = "UPDATE PUBLISHING_HOUSES SET  name = ?, city_id = ?  WHERE id = ?";
    public static final String SELECT_FROM_PUBLISHING_HOUSES = "SELECT * FROM PUBLISHING_HOUSES";
}
