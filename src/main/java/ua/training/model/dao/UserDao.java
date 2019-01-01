package ua.training.model.dao;

import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends AutoCloseable {
    User getUserById(int id);
    List<User> getUsersBySeanceId(int id);
    void addMoneyToUser(String username, int money);
    void createUser(UserDto user) throws SQLException;
    User getUserByUsernameAndPassword(String username, String password) throws SQLException;
    void close();
}
