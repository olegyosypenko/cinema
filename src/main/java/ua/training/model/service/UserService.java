package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

public class UserService {
    private CryptoService cryptoService = new CryptoService();

    public User getUserByUsernameAndPassword(String username, String password) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            String hashedPassword = cryptoService.encryptString(password);
            return userDao.getUserByUsernameAndPassword(username, hashedPassword);
        }
    }

    public void createUser(UserDto user) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            String hashedPassword = cryptoService.encryptString(user.getPassword());
            user.setPassword(hashedPassword);
            userDao.createUser(user);
        }
    }

    public void addMoneyByUserId(int id, int money) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            userDao.addMoneyToUser(id, money);
        }
    }

    public User getUserById(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            return userDao.getUserById(id);
        }
    }
}
