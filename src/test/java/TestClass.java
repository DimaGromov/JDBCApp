import connection.Connect;
import connection.ConnectionController;
import entity.Product;
import org.h2.tools.DeleteDbFiles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClass {
    /*
    private String DB_DRIVER = "org.postgresql.Driver";
    private String URL = "jdbc:postgresql://localhost:5432/service_db";
    private String USER_NAME = "admin";
    private String PASSWORD = "pass";


    private String DB_DRIVER = "org.h2.Driver";
    private String URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE;Mode=MySQL";
    private String USER_NAME = "sa";
    private String PASSWORD = "";
    */

    //Connect connect = new Connect(DB_DRIVER, URL, USER_NAME, PASSWORD);
    //ConnectionController connectionController = new ConnectionController(connect);
    //ProductService productService = new ProductService(connectionController, "Products");

    // XML
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    Connect connect = (Connect) applicationContext.getBean("connect");
    ConnectionController connectionController = (ConnectionController) applicationContext.getBean("connectionController");
    ProductService productService = (ProductService) applicationContext.getBean("productService");
    Product milk = (Product) applicationContext.getBean("milk");
    Product coffe = (Product) applicationContext.getBean("coffe");


    @Before
    public void before() throws SQLException {
        DeleteDbFiles.execute("~", "test", true);
        productService.createTable();
    }


   @Test
    public void create() throws SQLException {
        int result = productService.create(milk);
        result += productService.create(coffe);
        Assert.assertEquals(result, 2);
    }


    @Test
    public void read() throws SQLException {
        productService.create(milk);
        productService.create(coffe);
        ResultSet resultSet = productService.read();


    }


    @Test
    public void updateById() throws SQLException {
        productService.create(milk);
        int result = productService.updateById(1, coffe);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteByIndex() throws SQLException {
        productService.create(milk);
        productService.create(coffe);

        int result = productService.deleteById(1);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void hack() throws SQLException{
        int result = productService.create(milk);
        Assert.assertNotNull(result);
        Product product = hackProduct();

        productService.create(product);

        productService.read();

    }

    Product hackProduct() {
        String tableName = "";
        try{
            Field field = productService.getClass().getDeclaredField("tableName");
            field.setAccessible(true);
            tableName = (String) field.get(productService);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }


        // "INSERT INTO " + tableName + " (name, price, count) VALUES (\'" + product.getName() + "\', " + product.getPrice() + ", " + product.getCount() + ");"
        String hackCommand = "apple\', 10.0, 100); DROP TABLE " + tableName + "; CREATE TABLE tmp ( a VARCHAR(1), b DECIMAL(1, 1), c INT(1)); INSERT INTO tmp VALUES (\'t";
        Product product = new Product(hackCommand, 0.0, 0);
        return product;
    }


    @After
    public void after() {
        connectionController.closeConnection();
    }
}
