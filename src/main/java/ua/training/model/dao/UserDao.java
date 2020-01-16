package ua.training.model.dao;

import ua.training.model.dto.UserDto;
import ua.training.model.entity.User;

import java.util.Map;

public interface UserDao {
    void addMoneyToUser(int id, int money);

    void createUser(UserDto user);

    User getUserByUsernameAndPassword(String username, String password);

    void addMoneyToUsers(Map<User, Integer> refundForEachUser);

    User getUserById(int id);

    void withdrawMoney(int id, int fullPrice);
}
