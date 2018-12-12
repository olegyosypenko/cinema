package ua.training.controller;

import com.mysql.cj.jdbc.MysqlDataSource;
import ua.training.Main;
import ua.training.model.dao.mysql.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

@WebServlet(name = "MainServlet", urlPatterns = ("/serv"))
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = Main.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String str = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            if (!rs.next()) {
                str = "";
            }
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(4) + "  " + rs.getString(3));
//                str = rs.getString(2);
//            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        if (str == null || str.equals("")) str = "db error ";
        request.setAttribute("first", "лол");
        request.setAttribute("third", "sdfdsf");
        request.setAttribute("second", str);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
