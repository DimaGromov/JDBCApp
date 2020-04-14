import connection.ConnectionController;
import entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductService implements CRUD<Product> {

    private Connection connection;
    private Statement statement;
    private String tableName;

    ProductService(ConnectionController connectionController, String tableName) {
        connection = connectionController.createConnection();
        this.tableName = tableName;
    }

    public void createTable() throws SQLException {
        statement = connection.createStatement();
        String createTableComand = "CREATE TABLE " + tableName + " (" +
                "   id  INT(10) NOT NULL AUTO_INCREMENT, " +
                "   name    VARCHAR(10) NOT NULL, " +
                "   price   INT(10) NOT NULL, " +
                "   count   DECIMAL(10, 2)  NOT NULL);";

        statement.execute(createTableComand);
        statement.close();
    }

    @Override
    public int create(Product product) throws SQLException {
        Statement statement = connection.createStatement();
        String createComand = "INSERT INTO " + tableName + " (name, price, count) VALUES (\'" + product.getName() + "\', " + product.getPrice() + ", " + product.getCount() + ");";
        int result = statement.executeUpdate(createComand);
        return result;
    }

    @Override
    public int read() throws SQLException {
        Statement statement = connection.createStatement();
        String readAllComand = "SELECT * FROM " + tableName + ";";
        ResultSet resultSet = statement.executeQuery(readAllComand);

        int result = 0;
        while (resultSet.next()) {
            System.out.println("id = " + resultSet.getInt("id"));
            System.out.println("name = " + resultSet.getString("name"));
            System.out.println("price = " + resultSet.getDouble("price"));
            System.out.println("count = " + resultSet.getInt("count"));
            result++;
        }
        statement.close();
        return result;
    }

    @Override
    public int updateById(int id, Product product) throws SQLException {
        Statement statement = connection.createStatement();
        String updateByIdComand = "UPDATE " + tableName + " SET name = \'" + product.getName() + "\', " +
                "price = " + product.getPrice() + ", count = " + product.getCount() + " WHERE id = " + id + ";";

        int result = statement.executeUpdate(updateByIdComand);
        statement.close();
        return result;
    }

    @Override
    public int deleteById(int id) throws SQLException {
        statement = connection.createStatement();
        String deleteByIdComand = "DELETE FROM " + tableName + " WHERE id = " + id + ";";
        int result = statement.executeUpdate(deleteByIdComand);
        statement.close();
        return result;
    }
}
