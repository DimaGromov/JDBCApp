import entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableController {
    public static void createTable(Connection connection, Statement statement, String tableName) {
        try{
            statement = connection.createStatement();
            System.out.println("Statement create.");

            String createTableComand = "CREATE TABLE " + tableName + " (" +
                    "   id  INT(10) NOT NULL AUTO_INCREMENT, " +
                    "   name    VARCHAR(10) NOT NULL, " +
                    "   price   INT(10) NOT NULL, " +
                    "   count   DECIMAL(10, 2)  NOT NULL, " +
                    "   PRIMARY KEY(id));";

            statement.execute(createTableComand);
            System.out.println("Table " + tableName + " was create");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы.");
        }
    }

    public static int addRow(Connection connection, Statement statement, String tableName, Product product) {
        try{
            statement = connection.createStatement();
            String addRowCommand = "INSERT INTO " + tableName + " (name, price, count) VALUES (\'" + product.getName() + "\', " + product.getPrice() + ", " + product.getCount() + ");";
            int result = statement.executeUpdate(addRowCommand);
            return result;
        } catch (SQLException e) {
            System.out.println("Ошибка создания новой записи");
        }
        return 0;
    }

    public static ResultSet readAll(Connection connection, Statement statement, String tableName) {
        try {
            statement = connection.createStatement();

            String readAllComand = "SELECT * FROM " + tableName + ";";
            ResultSet resultSet = statement.executeQuery(readAllComand);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Ошибка чтения из таблицы.");
        }
        return null;
    }

    public static ResultSet readRowByIndex(Connection connection, Statement statement, String tableName, int id) {
        try{
            statement = connection.createStatement();
            String readRowByIndexComand = "SELECT * FROM " + tableName + " WHERE id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(readRowByIndexComand);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Ошибка чтения из таблицы");
        }
        return null;
    }

    public static int updateRowByIndex(Connection connection, Statement statement, String tableName, Product product,int id) {
        try{
            statement = connection.createStatement();
            String updateRowByIndexComand = "UPDATE " + tableName + " SET name = \'" + product.getName() + "\', " +
                    "price = " + product.getPrice() + ", count = " + product.getCount() + " WHERE id = " + id + ";";
            int result = statement.executeUpdate(updateRowByIndexComand);
            return result;
        } catch (SQLException e) {
            System.out.println("Ошибка чтения из таблицы");
        }
        return 0;
    }

    public static int deleteRowByIndex(Connection connection, Statement statement, String tableName, int id) {
        try{
            statement = connection.createStatement();
            String deleteRowByIndexComand = "DELETE FROM " + tableName + " WHERE id = " + id + ";";
            int result = statement.executeUpdate(deleteRowByIndexComand);
            return result;
        } catch (SQLException e) {
            System.out.println("Ошибка удаления.");
        }
        return 0;
    }

    public static void printProductResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try{
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id"));
                    System.out.println(resultSet.getString("name"));
                    System.out.println(resultSet.getDouble("price"));
                    System.out.println(resultSet.getInt("count"));
                    System.out.println("============================");
                }
            } catch (SQLException e) {
                System.out.println("Ошибка чтения из ResultSet");
                e.printStackTrace();
            }
        }

    }
}
