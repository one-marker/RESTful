package ru.spring.marker.rest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.spring.marker.rest.model.User;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(name, surname, birthday) VALUES (?,?,?)", new Object[] { user.getName(), user.getSurname(), user.getBirthday() } );
        logger.info("User successfully saved. User id: " + user);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET surname = ?, birthday = ? WHERE name = ?",new Object[] { user.getSurname(), user.getBirthday() , user.getName() } );
        logger.info("User successfully update by name. User name: " + user.getName());
    }

    @Override
    public void removeUser(String id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", Integer.decode(id));
        logger.info("User successfully removed. User id: " + id);
    }

    @Override
    public List<Map<String, Object>> getUserById(String id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM users WHERE id = ?", new Object[] { Integer.parseInt(id) });
        logger.info("User successfully received. User id: " + id);
        return list;
    }

    @Override
    public List<Map<String, Object>> listUsers() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM users");
        logger.info("Users successfully received");
        return list;
    }
}
