package ua.training.model.dao.mysql;

import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.util.List;

public class UserMySqlDao implements UserDao {
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

    @Override
    public void createUser(User user) {

    }
}
