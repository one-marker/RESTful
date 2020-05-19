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

/**
 * Service layer
 * This class implements transactional work of methods that have access to the database using @Transactional anotation
 */
@Service
public class CrudServiceImpl implements CrudService {

    /**
     * Data Access Object
     */
    private UserData userData;


    /**
     * This is the constructor needed to implement the UserData dependency.
     * @param userData Data Access Object
     */
    public void setUserData(UserData userData) {
        this.userData = userData;
    }


    /**
     * This method is tagged with @Transactional, meaning that any failure causes the entire
     * operation to roll back to its previous state and to re-throw the original exception.
     * @param user
     */
    @Override
    @Transactional
    public void addUser(User user) {
        userData.addUser(user);
    }

    /**
     * This method is tagged with @Transactional, meaning that any failure causes the entire
     * operation to roll back to its previous state and to re-throw the original exception.
     * @param user
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        userData.updateUser(user);
    }

    /**
     * This method is tagged with @Transactional, meaning that any failure causes the entire
     * operation to roll back to its previous state and to re-throw the original exception.
     * @param value
     */
    @Override
    @Transactional
    public void removeUser(String value) {
        userData.removeUser(value);
    }

    /**
     * This method is tagged with @Transactional, meaning that any failure causes the entire
     * operation to roll back to its previous state and to re-throw the original exception.
     * @param value
     * @return List<Map<String, Object>>
     */
    @Override
    @Transactional
    public List<Map<String, Object>> getUser(String value) {
        return userData.getUser(value);
    }

    /**
     * This method is tagged with @Transactional, meaning that any failure causes the entire
     * operation to roll back to its previous state and to re-throw the original exception.
     * @return  List<Map<String, Object>>
     */
    @Override
    @Transactional
    public List<Map<String, Object>> listUsers() {
        return userData.listUsers();
    }
}
