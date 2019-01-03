package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import java.sql.SQLException;

public class UserService implements AutoCloseable {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.getUserByUsernameAndPassword(username, password);
    }
    public void createUser(UserDto user) throws SQLException {
        UserDao userDao = daoFactory.createUserDao();
        userDao.createUser(user);
    }

    public void addMoneyByUserId(int id, int money) {
        UserDao userDao = daoFactory.createUserDao();
        userDao.addMoneyToUser(id, money);
    }

    public void close() {
        daoFactory.getTransaction().close();
    }
}
