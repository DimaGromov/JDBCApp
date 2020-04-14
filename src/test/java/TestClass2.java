import connection.Connect;
import connection.ConnectionController;
import entity.Product;
import org.h2.tools.DeleteDbFiles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class TestClass2 {
    /*
    private String DB_DRIVER = "org.postgresql.Driver";
    private String URL = "jdbc:postgresql://localhost:5432/service_db";
    private String USER_NAME = "admin";
    private String PASSWORD = "pass";
    */

    private String DB_DRIVER = "org.h2.Driver";
    private String URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE;Mode=MySQL";
    private String USER_NAME = "sa";
    private String PASSWORD = "";


    Connect connect = new Connect(DB_DRIVER, URL, USER_NAME, PASSWORD);
    ConnectionController connectionController = new ConnectionController(connect);
    ProductService productService = new ProductService(connectionController, "Products");

    @Before
    public void before() throws SQLException {
        DeleteDbFiles.execute("~", "test", true);
    }

    @Test
    public void createTable() throws SQLException {
        productService.createTable();
    }

    @Test
    public void create() throws SQLException {
        productService.createTable();
        Product milk = new Product("milk", 25.0, 100);
        Product coffe = new Product("coffe", 230.50, 20);
        int result = productService.create(milk);
        result += productService.create(coffe);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void read() throws SQLException {
        productService.createTable();
        Product milk = new Product("milk", 25.0, 100);
        Product coffe = new Product("coffe", 230.50, 20);
        productService.create(milk);
        productService.create(coffe);
        int result = productService.read();
        Assert.assertEquals(result, 2);
    }

    @Test
    public void updateById() throws SQLException {
        productService.createTable();
        Product milk = new Product("milk", 25.0, 100);
        Product coffe = new Product("coffe", 230.50, 20);
        productService.create(milk);
        int result = productService.updateById(1, coffe);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteByIndex() throws SQLException {
        productService.createTable();
        Product milk = new Product("milk", 25.0, 100);
        Product coffe = new Product("coffe", 230.50, 20);

        productService.create(milk);
        productService.create(coffe);

        int result = productService.deleteById(1);
        Assert.assertEquals(result, 1);
    }

    @After
    public void after() {
        connectionController.closeConnection();
    }
}
