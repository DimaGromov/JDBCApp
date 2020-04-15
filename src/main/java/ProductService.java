import connection.ConnectionController;
import entity.Product;

import java.sql.*;

public class ProductService implements CRUD<Product> {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String tableName;

    ProductService(ConnectionController connectionController, String tableName) {
        connection = connectionController.createConnection();
        this.tableName = tableName;
    }

    public void createTable() throws SQLException {
        String createTableComand = "CREATE TABLE " + tableName + " (" +
                "   id  INT(10) NOT NULL AUTO_INCREMENT, " +
                "   name    VARCHAR(300) NOT NULL, " +
                "   price   INT(10) NOT NULL, " +
                "   count   DECIMAL(10, 2)  NOT NULL);";

        preparedStatement = connection.prepareStatement(createTableComand);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public int create(Product product) throws SQLException {
        String createComand = "INSERT INTO " + tableName + " (name, price, count) VALUES(?, ?, ?);";

        preparedStatement = connection.prepareStatement(createComand);

        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getCount());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public ResultSet read() throws SQLException {
        String readAllComand = "SELECT * FROM " + tableName + ";";
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(readAllComand);

        while (resultSet.next()) {
            System.out.println("id = " + resultSet.getInt("id"));
            System.out.println("name = " + resultSet.getString("name"));
            System.out.println("price = " + resultSet.getDouble("price"));
            System.out.println("count = " + resultSet.getInt("count"));
        }
        return resultSet;
    }

    @Override
    public int updateById(int id, Product product) throws SQLException {
        String updateByIdComand = "UPDATE " + tableName + " SET name = ?, price = ?, count = ? WHERE id = ?;";
        preparedStatement = connection.prepareStatement(updateByIdComand);


        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2,product.getPrice());
        preparedStatement.setInt(3, product.getCount());
        preparedStatement.setInt(4, id);


        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int deleteById(int id) throws SQLException {

        String deleteByIdComand = "DELETE FROM " + tableName + " WHERE id = ?;";
        preparedStatement = connection.prepareStatement(deleteByIdComand);

        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
}
