package ru.spring.marker.rest.controller;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.spring.marker.rest.model.User;
import ru.spring.marker.rest.service.CrudService;
import ru.spring.marker.rest.service.CrudServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;

/**
 * This Controller class is required to listen for HTTP requests /message.
 */
@RestController
@RequestMapping("message")
class CrudController {

    private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

    private CrudService crudService;


    public CrudController() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        crudService = context.getBean("userService", CrudServiceImpl.class);
    }
    @Autowired
    public CrudController(@Qualifier("crudServiceImpl") CrudServiceImpl crudServiceImpl) {
        this.crudService = crudServiceImpl;
    }

    /**
     * This method handles HTTP GET requests /messege/{value}.
     * @param value
     * @return a list of users (JSON)
     */
    @GetMapping("{value}")
    public List<Map<String, Object>> getUserById(@PathVariable String value) {
        return crudService.getUser(value);
    }

    /**
     * This method handles HTTP GET requests /messege.
     * @return a list of users (JSON)
     */
    @GetMapping
    public List<Map<String, Object>> getUsers() {
        return crudService.listUsers();
    }

    /**
     * This method handles HTTP DELETE requests /messege.
     * @param value
     */
    @DeleteMapping("{value}")
    public void delete(@PathVariable String value) {
        crudService.removeUser(value);
    }

    /**
     * This method handles HTTP POST requests /messege.
     * @param request
     * @param requestString
     * @return User object (JSON)
     */
    @RequestMapping(method = RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User createUser(HttpServletRequest request, @RequestBody String requestString) {

        User user = null;

        switch (request.getContentType()){
            case "application/xml":
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    user = (User) jaxbUnmarshaller.unmarshal(new StringReader(requestString));
                    crudService.addUser(user);
                } catch (UnmarshalException e){
                    logger.info(e.getMessage());
                } catch (JAXBException e) {
                    logger.error(e.getMessage());
                }
                break;
            case "application/json":
                try {
                    Gson gson = new Gson();
                    user = gson.fromJson(requestString, User.class);
                    crudService.addUser(user);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
            default:
                logger.error("data format not defined");
                break;
        }

        return user;

    };

    /**
     * This method handles HTTP PUT requests /messege.
     * @param request
     * @param requestString
     * @return User object (JSON)
     */
    @RequestMapping(method = RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User update(HttpServletRequest request, @RequestBody String requestString) {

        User user = null;

        switch (request.getContentType()){
            case "application/xml":
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    user = (User) jaxbUnmarshaller.unmarshal(new StringReader(requestString));
                    crudService.updateUser(user);
                } catch (UnmarshalException e){
                    logger.error(e.getMessage());
                } catch (JAXBException e) {
                    logger.error(e.getMessage());
                }
                break;
            case "application/json":
                try {
                    Gson gson = new Gson();
                    user = gson.fromJson(requestString, User.class);
                    crudService.updateUser(user);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
            default:
                logger.error("data format not defined");
                break;
        }

        return user;
    }



}