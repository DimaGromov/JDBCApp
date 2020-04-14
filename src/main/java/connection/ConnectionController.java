package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController {
    private Connect connect;
    Connection connection = null;

    public ConnectionController(Connect connect) {
        this.connect = connect;
    }

    public Connection createConnection() {

        try{
            Class.forName(connect.getDB_DRIVER());
            connection = DriverManager.getConnection(connect.getURL(), connect.getUSER_NAME(), connect.getPASSWORD());
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка получения Connection от DriverManager.");
        }
        return null;
    }

    public void closeConnection() {
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
