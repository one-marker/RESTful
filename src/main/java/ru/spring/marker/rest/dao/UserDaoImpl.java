package ru.spring.marker.rest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
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

        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {

        logger.info("User successfully update. User details: " + user);
    }

    @Override
    public void removeUser(int id) {

        logger.info("User successfully removed. User details: " + id);
    }

    @Override
    public List<Map<String, Object>> getUserById(String id) {
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE id = ?", new Object[] { Integer.parseInt(id) });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {

        return null;
    }
}
