package ua.training.model.dao;

import ua.training.model.dto.UserDto;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getUserById(int id);
    List<User> getUsersBySeanceId(int id);
    void addMoneyToUser(int id, int money);
    void createUser(UserDto user);
    User getUserByUsernameAndPassword(String username, String password);
    void addMoneyToUsers(List<User> users, List<Integer> money);
    int getMoneyAmountById(int id);
    void withdrawMoney(int id, int fullPrice);
}
