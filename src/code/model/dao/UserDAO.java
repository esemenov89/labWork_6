package code.model.dao;

import code.model.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface UserDAO {
    User findUserByLoginAndPassword(String login, String password) throws SQLException;
    User findUserByLogin(String login) throws SQLException;
    User findUserByMail(String mail) throws SQLException;
    ArrayList<User> getAllUsers() throws SQLException;
    void addUser(User user) throws SQLException;
    void lockOrUnlockUser(String nick,int lock) throws SQLException;
}
