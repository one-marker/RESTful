package ru.spring.marker.rest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.spring.marker.rest.model.User;

import java.util.List;
import java.util.Map;

@Repository
public class UserDataSQLImpl implements UserData {

    private static final Logger logger = LoggerFactory.getLogger(UserDataSQLImpl.class);

    final
    JdbcTemplate jdbcTemplate;

    public UserDataSQLImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(name, surname, birthday) VALUES (?,?,?)",
                new Object[] { user.getName(), user.getSurname(), user.getBirthday() } );
        logger.info("User successfully saved. User id: " + user);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET surname = ?," +
                " birthday = ? WHERE name = ?",
                new Object[] { user.getSurname(), user.getBirthday() , user.getName() } );
        logger.info("User successfully update by name. User name: " + user.getName());
    }

    @Override
    public void removeUser(String value) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", Integer.decode(value));
        logger.info("User successfully removed. User id: " + value);
    }


    @Override
    public List<Map<String, Object>> getUser(String value) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM users WHERE id = ?", new Object[] { Integer.parseInt(value) });
        logger.info("User successfully received. User id: " + value);
        return list;
    }

    @Override
    public List<Map<String, Object>> listUsers() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM users");
        logger.info("Users successfully received");
        return list;
    }
}
