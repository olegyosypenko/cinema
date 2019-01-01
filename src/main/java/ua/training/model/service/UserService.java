package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import java.sql.SQLException;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getUserByUsernameAndPassword(username, password);
        }
    }
    public void createUser(UserDto user) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.createUser(user);
        }
    }

    public void addMoneyByUsername(String username, int money) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.addMoneyToUser(username, money);
        }
    }
}
