package ru.spring.marker.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.marker.rest.dao.UserDao;
import ru.spring.marker.rest.model.User;

import java.util.List;
import java.util.Map;

@Service
public class CrudServiceImpl implements CrudService {
    String a = "sad";

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getA() {
        return a;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(String id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<Map<String, Object>> listUsers() {
        return userDao.listUsers();
    }
}
