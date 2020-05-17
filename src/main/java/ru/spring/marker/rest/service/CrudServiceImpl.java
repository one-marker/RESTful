package ru.spring.marker.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;
import ru.spring.marker.rest.dao.UserData;
import ru.spring.marker.rest.model.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CrudServiceImpl implements CrudService {

    private UserData userData;
    public void setUserData(UserData userData) {
        this.userData = userData;
    }



    @Override
    @Transactional
    public void addUser(User user) {

        userData.addUser(user);

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userData.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(String value) {
        userData.removeUser(value);
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getUser(String value) {
        return userData.getUser(value);
    }

    @Override
    @Transactional
    public List<Map<String, Object>> listUsers() {

        return userData.listUsers();
    }
}
