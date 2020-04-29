package ru.spring.marker.rest.controller;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import ru.spring.marker.rest.model.User;
import ru.spring.marker.rest.model.Users;
import ru.spring.marker.rest.service.CrudServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;

@RestController
@RequestMapping("message")
class CrudController {


    private CrudServiceImpl crudService;

    public CrudController() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        crudService = context.getBean("userService", CrudServiceImpl.class);

    }

    @GetMapping("{id}")
    public List<Map<String, Object>> getUserById(@PathVariable String id) {
        return crudService.getUserById(id);
    }

    @GetMapping
    public List<Map<String, Object>> getUsers() {
        return crudService.listUsers();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        crudService.removeUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User createUser(HttpServletRequest request, @RequestBody String requestString) {

        User user = null;

        switch (request.getContentType()){
            case "application/xml":

                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    user = (User) jaxbUnmarshaller.unmarshal(new StringReader(requestString));


                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "application/json":
                Gson gson = new Gson();
                user = gson.fromJson(requestString, User.class);

                break;

        }

        crudService.addUser(user);

        return user;

    };
    @RequestMapping(method = RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User update(HttpServletRequest request, @RequestBody String requestString) {

        User user = null;

        switch (request.getContentType()){
            case "application/xml":
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    user = (User) jaxbUnmarshaller.unmarshal(new StringReader(requestString));

                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "application/json":
                Gson gson = new Gson();
                user = gson.fromJson(requestString, User.class);

                break;
        }

        crudService.updateUser(user);

        return user;
    }



}