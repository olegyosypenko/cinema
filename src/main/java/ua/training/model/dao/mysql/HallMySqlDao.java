package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.HallDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Hall;

import java.sql.*;

public class HallMySqlDao implements HallDao {

    private final Connection connection;
    private Logger logger = Logger.getLogger(HallMySqlDao.class);

    HallMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Hall getHallById(int id) {
        String query = BundleHolder.getBundle().getString("get.hall.query");

        Hall hall = new Hall();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                hall.setId(rs.getInt(1));
                hall.setRows(rs.getInt(2));
                hall.setColumns(rs.getInt(3));
            } else {
                logger.debug("Log not found for id: " + id);
                throw new DaoException("Hall not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return hall;
    }
}
