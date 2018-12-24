package ua.training.model.service;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mysql.UserMySqlDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import java.sql.SQLException;

public class UserService {
    UserDao userDao = new UserMySqlDao();
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        return userDao.getUserByUsernameAndPassword(username, password);
    }
    public void createUser(UserDto user) throws SQLException {
        userDao.createUser(user);
    }
}
