package ua.training.model.dao.mysql;

import ua.training.model.BundlePool;
import ua.training.model.dao.HallDao;
import ua.training.model.entity.Hall;

import java.sql.*;

public class HallMySqlDao implements HallDao {

    private final Connection connection;

    public HallMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Hall getHallById(int id) {
        String query = BundlePool.getBundle().getString("get.hall.query");

        Hall hall = new Hall();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                hall.setId(rs.getInt(1));
                hall.setRows(rs.getInt(2));
                hall.setColumns(rs.getInt(3));
            } else {
                throw new RuntimeException("Incorrect id");
            }
        } catch (SQLException e) {

        }
        return hall;
    }
}
