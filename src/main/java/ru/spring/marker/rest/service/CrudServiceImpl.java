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

    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {

    }

    @Override
    @Transactional
    public void removeUser(int id) {

    }

    @Override
    @Transactional
    public List<Map<String, Object>> getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listBooks() {
        return null;
    }
}
