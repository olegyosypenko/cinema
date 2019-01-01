package ua.training.model.dao.mysql;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static volatile DataSource dataSource; //TODO check if volatile is needed

    private static final String URL; //TODO field is nullable. need to check for exceptional situations
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("mysql_data");
        URL = bundle.getString("url");
        USERNAME = bundle.getString("username");
        PASSWORD = bundle.getString("password");
    }
    public static DataSource getDataSource(){

        if(dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    basicDataSource.setUrl(URL);
                    basicDataSource.setUsername(USERNAME);
                    basicDataSource.setPassword(PASSWORD);
                    basicDataSource.setMinIdle(5);
                    basicDataSource.setMaxIdle(10);
                    dataSource = basicDataSource;
                }
            }
        }

        return dataSource;
    }
}