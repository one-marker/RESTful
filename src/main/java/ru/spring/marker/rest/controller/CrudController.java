package ru.spring.marker.rest.controller;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.spring.marker.rest.model.User;
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

    private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

    private CrudServiceImpl crudService;

    public CrudController() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        crudService = context.getBean("userService", CrudServiceImpl.class);

    }

    @GetMapping("{value}")
    public List<Map<String, Object>> getUserById(@PathVariable String value) {
        return crudService.getUser(value);
    }

    @GetMapping
    public List<Map<String, Object>> getUsers() {
        return crudService.listUsers();
    }

    @DeleteMapping("{value}")
    public void delete(@PathVariable String value) {
        crudService.removeUser(value);
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


                } catch (javax.xml.bind.UnmarshalException e){
                    logger.info(requestString);
                    return user;

                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                break;
            case "application/json":
                Gson gson = new Gson();
                user = gson.fromJson(requestString, User.class);

                break;
            default:
                logger.info(requestString);
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