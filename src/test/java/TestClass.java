import connection.ConnectionController;
import entity.Product;
import org.h2.tools.DeleteDbFiles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class TestClass {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE;Mode=MySQL";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    static final String TABLE_NAME = "Products";

    Connection connection;
    Statement statement;

/*
    @Before
    public void createConnection() throws SQLException {
        try {
            DeleteDbFiles.execute("~", "test", true);
        } catch (SQLException e) {
            System.out.println("Ошибка очисти БД");
        }
        connection = ConnectionController.createConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
        System.out.println("Connection was create.");
    }

    @Test
    public void addInTable() {
        TableController.createTable(connection, statement, TABLE_NAME);
        int result = TableController.addRow(connection, statement, TABLE_NAME, new Product("milk", 50.0, 10));
        Assert.assertEquals(result, 1);
    }

    @Test
    public void readFromTableById() {
        TableController.createTable(connection, statement, TABLE_NAME);
        TableController.addRow(connection, statement, TABLE_NAME, new Product("milk", 50.0, 10));
        TableController.addRow(connection, statement, TABLE_NAME, new Product("tea", 100.0, 20));
        ResultSet resultSet = TableController.readRowByIndex(connection, statement, TABLE_NAME, 1);
        TableController.printProductResultSet(resultSet);
    }

    @Test
    public void updateRowByIndex() {
        TableController.createTable(connection, statement, TABLE_NAME);
        TableController.addRow(connection, statement, TABLE_NAME, new Product("milk", 50.0, 10));
        TableController.addRow(connection, statement, TABLE_NAME, new Product("tea", 100.0, 20));
        ResultSet resultSet = TableController.readAll(connection, statement, TABLE_NAME);
        TableController.printProductResultSet(resultSet);

        TableController.updateRowByIndex(connection, statement,  TABLE_NAME, new Product("candys", 200.0, 1000), 1);

        ResultSet resultSet1 = TableController.readAll(connection, statement, TABLE_NAME);
        TableController.printProductResultSet(resultSet1);
    }

    @Test
    public void deleteByIndex() {
        TableController.createTable(connection, statement, TABLE_NAME);
        TableController.addRow(connection, statement, TABLE_NAME, new Product("milk", 50.0, 10));
        TableController.addRow(connection, statement, TABLE_NAME, new Product("tea", 100.0, 20));
        ResultSet resultSet = TableController.readAll(connection, statement, TABLE_NAME);
        TableController.printProductResultSet(resultSet);

        TableController.deleteRowByIndex(connection, statement, TABLE_NAME, 1);

        ResultSet resultSet1 = TableController.readAll(connection, statement, TABLE_NAME);
        TableController.printProductResultSet(resultSet1);
    }



    @After
    public void closeConnection() throws SQLException {
        statement.close();
        ConnectionController.closeConnection();
        System.out.println("Connection was close.");
    }
*/

}
