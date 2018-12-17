package ua.training.model.dao.mysql;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static volatile Connection connection; //TODO check if volatile is needed
    private static final String URL; //TODO field is nullable. need to check for exceptional situations
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("mysql_data");
        URL = bundle.getString("url");
        USERNAME = bundle.getString("username");
        PASSWORD = bundle.getString("password");
    }
    public static Connection getConnection(){

        if(connection == null) {
            synchronized (ConnectionPool.class){
                if (connection == null){

                    BasicDataSource dataSource = new BasicDataSource();
                    dataSource.setUrl(URL);
                    dataSource.setUsername(USERNAME);
                    dataSource.setPassword(PASSWORD);
                    dataSource.setMinIdle(5);
                    dataSource.setMaxIdle(10);
                    try {
                        connection = dataSource.getConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return connection;
    }
}