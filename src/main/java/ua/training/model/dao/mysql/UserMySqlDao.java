package ua.training.model.dao.mysql;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import ua.training.model.BundlePool;
import ua.training.model.dao.UserDao;
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
    private static final String GET_BY_USERNAME_PASSWORD = "SELECT * FROM users WHERE username=? AND password=?;";

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
                statement.setInt(1, id);
                statement.setInt(2, money);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        logger.info("Thread name in UserDAO" + Thread.currentThread().getName());
        String hashedPassword = hashPassword(password);
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_USERNAME_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setRole(Role.valueOf(rs.getString(4)));
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
    public void createUser(UserDto user) {

        String hashedPassword = hashPassword(user.getPassword());
        try {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, hashedPassword);
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
            throw new UsernameIsTakenException();
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        }
    }

    private String hashPassword(String password) {
        String salt = "1234";
        int iterations = 10000;
        int keyLength = 512;
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec( passwordChars, saltBytes, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return Hex.encodeHexString(res);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            logger.error("Hash algorithm does not work");
            throw new RuntimeException(e);
        }
    }
}
