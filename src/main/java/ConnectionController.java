import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static Connection connection = null;

    public static Connection createConnection(String DB_URL, String USER, String PASS) {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка получения Connection от DriverManager.");
        }
        return null;
    }

    public static void closeConnection() {
        if (connection != null) {
            try{
                connection.close();
            } catch (SQLException e) {
                System.out.println("Ошибка закрытия Connection.");
            }
        } else {
            System.out.println("Не найденно созданного Connection.");
        }
    }
}
