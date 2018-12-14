package ua.training.model.dao;

import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getUserById(int id);
    List<User> getUsersBySeanceId(int id);
    void addMoneyToUser(long amount, int id);
    void createUser(User user) throws SQLException;
    User getUserByUsernameAndPassword(String username, String password) throws SQLException;
}
