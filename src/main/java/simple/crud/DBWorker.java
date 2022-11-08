package simple.crud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBWorker {
    static Properties property = new Properties();
    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/database.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    String URL = property.getProperty("datasource.url");
    String USERNAME = property.getProperty("datasource.username");
    String PASSWORD = property.getProperty("datasource.password");

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker() throws FileNotFoundException {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
