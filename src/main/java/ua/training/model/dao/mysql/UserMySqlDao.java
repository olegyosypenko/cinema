package ua.training.model.dao.mysql;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import ua.training.model.BundlePool;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.List;

public class UserMySqlDao implements UserDao {
    private static final Logger logger = Logger.getLogger(UserMySqlDao.class);
    private static final String CREATE_USER = "INSERT INTO users(username, password, role, money, first_name, first_name_en, second_name, second_name_en) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final Connection connection;

    public UserMySqlDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getUsersBySeanceId(int id) {
        return null;
    }

    @Override
    public void addMoneyToUser(int id, int money) {
        String query = BundlePool.getBundle().getString("update.money.query");
        try {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, money);
                statement.setInt(2, id);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }
    @Override
    public void withdrawMoney(int id, int money) {
        String query = BundlePool.getBundle().getString("withdraw.money.query");
        try {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, money);
                statement.setInt(2, id);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String query = BundlePool.getBundle().getString("select.user.by.username.password.query");
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setRole(Role.valueOf(rs.getString(3)));
                user.setMoney(rs.getInt(4));
                user.setFirstName(rs.getString(5));
                user.setLastName(rs.getString(6));
            } else {
                throw new DaoException("User not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
        return user;
    }

    @Override
    public void addMoneyToUsers(List<User> users, List<Integer> money) {
        logger.debug("Number of users: " + users.size());
        logger.debug("Number of money items: " + money.size());
        String query = BundlePool.getBundle().getString("update.money.query");
        try {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < users.size() && i < money.size(); i++) {
                    statement.setInt(1, money.get(i));
                    statement.setInt(2, users.get(i).getId());
                    statement.addBatch();
                    statement.clearParameters();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    @Override
    public int getMoneyAmountById(int id) {
        int result;
        String query = BundlePool.getBundle().getString("select.money.amount.by.id.query");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            } else {
                throw new DaoException("User does not exists");
            }
        } catch (SQLException e) {
            logger.error("Cannot execute query");
            throw new DaoException("Cannot execute query", e);
        }
        return result;
    }

    @Override
    public void createUser(UserDto user) {

        try {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, Role.USER.name());
                statement.setLong(4, 0);
                statement.setString(5, user.getFirstName());
                statement.setString(6, user.getFirstNameEN());
                statement.setString(7, user.getLastName());
                statement.setString(8, user.getLastNameEN());
                statement.execute();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Not unique value", e);
            throw new NotUniqueValueException(e);
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }
}
