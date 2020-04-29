package ru.spring.marker.rest.dao;

import ru.spring.marker.rest.model.User;
import java.util.List;
import java.util.Map;

public interface UserDao {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(String id);

    public List<Map<String, Object>> getUserById(String id);

    public List<Map<String, Object>> listUsers();
}
