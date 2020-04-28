package ru.spring.marker.rest.service;

import ru.spring.marker.rest.model.User;

import java.util.List;
import java.util.Map;

public interface CrudService {
    public void addUser(User user);

    public void updateUser(int id, User user);

    public void removeUser(int id);

    public List<Map<String, Object>> getUserById(String id);

    public List<User> listBooks();
}
