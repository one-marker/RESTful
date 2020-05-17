package ru.spring.marker.rest.dao;

import org.xml.sax.SAXException;
import ru.spring.marker.rest.model.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserData {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(String id);

    public List<Map<String, Object>> getUser(String value);

    public List<Map<String, Object>> listUsers() ;
}
