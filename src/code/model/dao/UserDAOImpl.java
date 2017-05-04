package code.model.dao;


import org.apache.log4j.Logger;

import code.model.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import code.services.ConnectionPool;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Class for data exchange with database
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    /**
     * Find user with login=login and password=password in database
     * @param login of user what we search
     * @param password
     * @return
     */
    @Override
    public User findUserByLoginAndPassword(String login, String password) throws SQLException {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS WHERE LOWER(NICK) = '"+login.toLowerCase()+"' AND PASSWORD = '"+password+"'")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            LOGGER.debug("user " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return user;
    }

    /**
     * Find user with login=login in database
     * @param login
     * @return
     */
    @Override
    public User findUserByLogin(String login) throws SQLException {
        User user = null;

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection
                    .prepareStatement( "SELECT * FROM USERS WHERE LOWER(NICK) = '"+login.toLowerCase()+"'");

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            LOGGER.debug("user " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return user;
    }

    /**
     * Method for lock or unlock user
     * @param login - of user
     * @param lock - if 1, then user will be locked, if 0, then user will be unlocked
     */
    @Override
    public void lockOrUnlockUser(String login,int lock) throws SQLException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "UPDATE USERS SET locked=? WHERE NICK = ?")) {

            statement.setInt(1,lock);
            statement.setString(2,login);
            statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
        catch (Exception e){
            LOGGER.error(e);
        }
    }

    /**
     * Find user with mail=mail in database
     * @param mail
     * @return
     */
    @Override
    public User findUserByMail(String mail) throws SQLException {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS WHERE LOWER(MAIL) = '"+mail.toLowerCase()+"'")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            LOGGER.debug("user " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return user;
    }

    /**
     * Add user in database
     * @param user
     */
    @Override
    public void addUser(User user) throws SQLException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "INSERT INTO USERS(NICK,MAIL,PASSWORD,ACCOUNT_TYPE,LOCKED) VALUES(?,?,?,?,?)")) {
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getMail());
            statement.setString(3,user.getPassword());
            statement.setInt(4,user.getAccountType());
            statement.setInt(5,user.getLocked());
            statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
        catch (Exception e){
            LOGGER.error(e);
        }
    }

    /**
     * Get all user from database
     * @return
     */
    public ArrayList<User> getAllUsers() throws SQLException {
        User user=null;
        ArrayList<User> users= new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = createEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return users;
    }

    /**
     * Create user from data of database
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private User createEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("NICK"),
                resultSet.getString("MAIL"),
                resultSet.getString("PASSWORD"),
                resultSet.getInt("ACCOUNT_TYPE"),
                resultSet.getInt("LOCKED"));
    }
}
