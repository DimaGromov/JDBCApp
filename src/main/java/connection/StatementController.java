package connection;

import java.sql.SQLException;
import java.sql.Statement;

public class StatementController {
    private ConnectionController connectionController;
    private Statement statement;

    StatementController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    public Statement createStatement() throws SQLException {
        return connectionController.createConnection().createStatement();
    }
}
