package code.services;

import code.model.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface UserService {

    User auth(String login, String password) throws SQLException;
    ArrayList<User> getAllUsers() throws SQLException;
    User validateUser(String login, String password, String mail) throws SQLException;
    void addUser(User user) throws SQLException;
    void lockOrUnlockUser(String nick,int lock) throws SQLException;
}
