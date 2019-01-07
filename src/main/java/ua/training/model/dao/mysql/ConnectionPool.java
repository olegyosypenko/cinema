package ua.training.model.dao.mysql;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

class ConnectionPool {
    private static volatile DataSource dataSource;

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final int MAX_IDLE_CONNECTIONS;
    private static final int MIN_IDLE_CONNECTIONS;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        URL = bundle.getString("url");
        USERNAME = bundle.getString("username");
        PASSWORD = bundle.getString("password");
        MAX_IDLE_CONNECTIONS = Integer.parseInt(bundle.getString("max.idle.connections"));
        MIN_IDLE_CONNECTIONS = Integer.parseInt(bundle.getString("min.idle.connections"));
    }
    static DataSource getDataSource(){

        if(dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    basicDataSource.setUrl(URL);
                    basicDataSource.setUsername(USERNAME);
                    basicDataSource.setPassword(PASSWORD);
                    basicDataSource.setMinIdle(MIN_IDLE_CONNECTIONS);
                    basicDataSource.setMaxIdle(MAX_IDLE_CONNECTIONS);
                    dataSource = basicDataSource;
                }
            }
        }

        return dataSource;
    }
}