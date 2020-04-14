import java.sql.ResultSet;
import java.sql.SQLException;

public interface CRUD <T> {
    int create(T t) throws SQLException;
    int read() throws SQLException;
    int updateById(int id, T t) throws  SQLException;
    int deleteById(int id) throws SQLException;
}
