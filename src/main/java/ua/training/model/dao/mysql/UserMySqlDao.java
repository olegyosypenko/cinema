package ua.training.model.dao.mysql;

import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.List;

public class UserMySqlDao implements UserDao {
    public static final String CREATE_USER = "INSERT INTO users(login, password, role, money, first_name, second_name) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String GET_LAST_ID = "SELECT id FROM users WHERE login=?;";
    public static final String GET_BY_USERNAME_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?;";
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getUsersBySeanceId(int id) {
        return null;
    }

    @Override
    public void addMoneyToUser(long amount, int id) {

    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        User user = new User();
        Connection connection = ConnectionPool.getDataSource().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_USERNAME_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(User.Role.valueOf(rs.getString(4)));
                user.setMoney(rs.getInt(5));
                user.setFirstName(rs.getString(6));
                user.setLastName(rs.getString(7));
            } else {
                throw new IncorrectUsernameOrPasswordException();
            }
        }

        return user;
    }
    @Override
    public void createUser(User user) throws SQLException {
        Connection connection = ConnectionPool.getDataSource().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().name());
            statement.setLong(4, user.getMoney());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole().name());
            statement.execute();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_LAST_ID)) {
            statement.setString(1, user.getLogin());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }
}
