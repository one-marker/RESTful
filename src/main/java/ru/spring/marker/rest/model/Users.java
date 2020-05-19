package ru.spring.marker.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * This class is special data representing a group of users. Required for parsing an XML file
 */
@XmlRootElement (name = "Users")
public class Users{

    @XmlElement (name = "User")
    ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }


}
