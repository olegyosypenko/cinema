package ua.training.model.dao.mysql;

import org.apache.log4j.Logger;
import ua.training.model.BundleHolder;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.List;

public class UserMySqlDao implements UserDao {
    private static final Logger logger = Logger.getLogger(UserMySqlDao.class);
    private final Connection connection;

    public UserMySqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addMoneyToUser(int id, int money) {
        String query = BundleHolder.getBundle().getString("update.money.query");
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
        String query = BundleHolder.getBundle().getString("withdraw.money.query");
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
        String query = BundleHolder.getBundle().getString("select.user.by.username.password.query");
        User user;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User.Builder builder = new User.Builder();
                builder.setId(rs.getInt(1))
                        .setUsername(rs.getString(2))
                        .setRole(Role.valueOf(rs.getString(3)))
                        .setMoney(rs.getInt(4))
                        .setFirstName(rs.getString(5))
                        .setLastName(rs.getString(6));
                user = builder.build();
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
        String query = BundleHolder.getBundle().getString("update.money.query");
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
    public User getUserById(int id) {
        User user;
        String query = BundleHolder.getBundle().getString("select.user.by.id");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User.Builder builder = new User.Builder();
                builder.setId(resultSet.getInt(1))
                        .setUsername(resultSet.getString(2))
                        .setRole(Role.valueOf(resultSet.getString(3)))
                        .setMoney(resultSet.getInt(4))
                        .setFirstName(resultSet.getString(5))
                        .setLastName(resultSet.getString(6));
                user = builder.build();
            } else {
                throw new DaoException("User not found");
            }
        } catch (SQLException e) {
            logger.error("Cannot execute query");
            throw new DaoException("Cannot execute query", e);
        }
        return user;
    }

    @Override
    public void createUser(UserDto user) {
        String query = BundleHolder.getBundle().getString("create.user.query");
        try {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
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

    public void deleteUserByUsername(String username) {
        String query = BundleHolder.getBundle().getString("delete.user.query");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Cannot execute query", e);
        }
    }
}
