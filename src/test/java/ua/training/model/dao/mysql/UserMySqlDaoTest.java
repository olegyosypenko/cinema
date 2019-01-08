package ua.training.model.dao.mysql;

import org.junit.Test;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.exceptions.NotUniqueValueException;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UserMySqlDaoTest {

    @Test
    public void getUserById() throws SQLException {
        UserMySqlDao userMySqlDao = new UserMySqlDao(ConnectionPool.getDataSource().getConnection());
        User user = userMySqlDao.getUserById(30);
        assertEquals(user.getUsername(), "qwerty");
    }



    @Test(expected = DaoException.class)
    public void getUserByUsernameAndPassword() throws SQLException {
        UserMySqlDao userMySqlDao = new UserMySqlDao(ConnectionPool.getDataSource().getConnection());
        userMySqlDao.getUserByUsernameAndPassword("fffff", "fffff");
    }

    @Test(expected = NotUniqueValueException.class)
    public void createUser() throws SQLException {
        UserDto user = new UserDto();
        user.setUsername("qwerty");
        UserMySqlDao userMySqlDao = new UserMySqlDao(ConnectionPool.getDataSource().getConnection());
        userMySqlDao.createUser(user);
    }
}