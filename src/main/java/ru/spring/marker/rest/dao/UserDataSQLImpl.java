package ru.spring.marker.rest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.spring.marker.rest.model.User;

import java.util.List;
import java.util.Map;

/**
 * Implementation of the UserData interface providing to work with the PgSQL
 * For PostgreSQL: in bean id="userData" set class="ru.spring.marker.rest.dao.UserDataSQLImpl"
 */
@Repository
public class UserDataSQLImpl implements UserData {

    private static final Logger logger = LoggerFactory.getLogger(UserDataSQLImpl.class);

    /**
     *  template class that does all the database interactions
     */
    JdbcTemplate jdbcTemplate;

    /**
     * This is the constructor needed to implement the jdbcTemplate dependency.
     * @param jdbcTemplate template class that does all the database interactions
     */
    @Autowired
    public UserDataSQLImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method adds the user to the database.
     * @param user Type of data representing one user
     */
    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(name, surname, birthday) VALUES (?,?,?)",
                new Object[] { user.getName(), user.getSurname(), user.getBirthday() } );
        logger.info("User successfully saved. User id: " + user);
    }

    /**
     * This method updates user data. In the database, the user is searched by name field
     * @param user Type of data representing one user
     */
    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET surname = ?," +
                " birthday = ? WHERE name = ?",
                new Object[] { user.getSurname(), user.getBirthday() , user.getName() } );
        logger.info("User successfully update by name. User name: " + user.getName());
    }

    /**
     * This method removes the user by id field.
     * @param value The id value
     */
    @Override
    public void removeUser(String value) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", Integer.decode(value));
        logger.info("User successfully removed. User id: " + value);
    }

    /**
     * This method gets a list of users by id field
     * @param value The id value
     * @return a list of users
     */
    @Override
    public List<Map<String, Object>> getUser(String value) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM users WHERE id = ?", new Object[] { Integer.parseInt(value) });
        logger.info("User successfully received. User id: " + value);
        return list;
    }

    /**
     * This method gets a complete list of users
     * @return a list of users
     */
    @Override
    public List<Map<String, Object>> listUsers() {

        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM users");
        logger.info("Users successfully received");
        return list;
    }
}
