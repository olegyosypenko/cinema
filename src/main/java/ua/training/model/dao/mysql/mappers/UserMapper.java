package ua.training.model.dao.mysql.mappers;

import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used to map resultSet to user
 */
public class UserMapper {

    /**
     * Creates user from result set
     *
     * @param resultSet resultSet from DB
     * @return User
     */
    public static User mapUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new User.Builder()
                    .setId(resultSet.getInt(1))
                    .setUsername(resultSet.getString(2))
                    .setRole(Role.valueOf(resultSet.getString(3)))
                    .setMoney(resultSet.getInt(4))
                    .setFirstName(resultSet.getString(5))
                    .setLastName(resultSet.getString(6))
                    .build();
        } else {
            throw new DaoException("User not found");
        }
    }
}
