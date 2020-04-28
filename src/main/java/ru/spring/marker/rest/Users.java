package ru.spring.marker.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement (name = "Users")
public class Users{

    @XmlElement (name = "User")
    ArrayList<User> users;


    public ArrayList<User> getUsers() {
        return users;
    }


}
