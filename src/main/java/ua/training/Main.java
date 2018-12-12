package ua.training;

import com.mysql.cj.jdbc.MysqlDataSource;
import ua.training.model.dao.mysql.ConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = Main.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(4) + "  " + rs.getString(3));
                str = rs.getString(2);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        if (str == null || str.equals("")) str = "db error ";
    }
    public static Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ResourceBundle bundle = ResourceBundle.getBundle("mysql_data");
        String URL = bundle.getString("url");
        String USERNAME = bundle.getString("username");
        String PASSWORD = bundle.getString("password");
        ds.setUrl(URL);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        try {
            ds.setUseSSL(false);
        } catch (Exception e) {

        }
        Connection con = ds.getConnection();
        return con;
    }
}
