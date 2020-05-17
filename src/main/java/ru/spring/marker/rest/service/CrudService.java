package ru.spring.marker.rest.service;

import ru.spring.marker.rest.model.User;

import java.util.List;
import java.util.Map;

public interface CrudService {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(String value);

    public List<Map<String, Object>> getUser(String value);

    public List<Map<String, Object>> listUsers();
}
