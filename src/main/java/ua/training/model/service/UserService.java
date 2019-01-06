package ua.training.model.service;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.UserDao;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class UserService {
    private Logger logger = Logger.getLogger(UserService.class);
    public User getUserByUsernameAndPassword(String username, String password) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            String hashedPassword = hashPassword(password);
            return userDao.getUserByUsernameAndPassword(username, hashedPassword);
        }
    }
    public void createUser(UserDto user) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            String hashedPassword = hashPassword(user.getPassword());
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

    public int getMoneyAmountById(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.createUserDao();
            return userDao.getMoneyAmountById(id);
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
            throw new ServiceException("Hash algorithm does not work", e);
        }
    }
}
