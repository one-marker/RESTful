package ru.spring.marker.rest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import ru.spring.marker.rest.service.CrudServiceImpl;

import java.util.*;

@RestController
@RequestMapping("message")
class CrudController {


//    @Autowired
//    JdbcTemplate jdbcTemplate;
    private CrudServiceImpl crudService;

    private final static Logger logger = LoggerFactory.getLogger(CrudController.class);






    private PlatformTransactionManager platformTransactionManager;

   // @Autowired
    public CrudController() {
        //this.jdbcTemplate = jdbcTemplate;

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        crudService = context.getBean("userService", CrudServiceImpl.class);
    //    System.out.println(context.getBean("userService", CrudServiceImpl.class).getA());
    }

    @GetMapping("{id}")
    public List<Map<String, Object>> getUserById(@PathVariable String id) {
        return crudService.getUserById(id);
    }
    @GetMapping
    public int list() {


        System.out.println(crudService.getA());
        //jdbcTemplate

//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
        //return messages;
        return 1;
    }
//    @GetMapping
//    public List<Map<String, Object>> list() {
//
//
//
//        List results = jdbcTemplate.queryForList("SELECT * FROM users");
//        //jdbcTemplate
//
////                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
////        ).forEach(customer -> log.info(customer.toString()));
//        //return messages;
//        return jdbcTemplate.queryForList("SELECT * FROM users");
//    }
//
//    @GetMapping("{id}")
//    public List<Map<String, Object>> getOne(@PathVariable String id) {
//        return jdbcTemplate.queryForList("SELECT * FROM users WHERE id = ?", new Object[] { "id" });
//    }
//
//
//    @Transactional
//    public void book(String... persons) {
//        for (String person : persons) {
//            logger.info("Booking " + person + " in a seat...");
//            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
//        }
//    }
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE,
//            MediaType.APPLICATION_XML_VALUE})
//    public int createUser(HttpServletRequest request, @RequestBody String user) {
//        var content = request.getContentType();
//
////        String jsonStr = user;
////        Gson gson = new Gson();
////        User clicks[] = gson.fromJson(jsonStr, User[].class);
//
//
////        try {
////            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
////
////            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
////
////            Users employee = (Users) jaxbUnmarshaller.unmarshal(new StringReader(user));
//////            for (int i = 0; i < employee.getUsers().size(); i++) {
//////                logger.info("XML " + employee.getUsers().get(i).getName());
//////            }
////            logger.info("XML " + content);
////
////           // logger.info("XML " + employee.getUsers().get(1).getName() + " in a seat...");
////            // System.out.println(employee//.getName());
////
////
////            //  logger.info("Booking " + user.getName() + " in a seat...");
////
////
////        } catch (JAXBException e) {
////            e.printStackTrace();
////        }
//        return 1;
//    }
////    @Transactional
////    @PostMapping(
////            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
////            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
////    )
////    public int create(@RequestBody User requestUserDetails) {
////
////        logger.info("Booking " + requestUserDetails.toString() + " in a seat...");
////
////      //  jdbcTemplate.update("INSERT INTO users(name, surname) VALUES (?,?)", message.values().toArray());
////
//////        //Create TransactionDefinition
//////        DefaultTransactionDefinition defaultTransactionDefinition =
//////                new DefaultTransactionDefinition();
//////        //Set name of your transaction
//////        defaultTransactionDefinition.setName("deletePersonTransaction");
//////        //Set transaction properties. Here we are setting
//////        //PROPAGATION_REQUIRED - (Support current transaction. Ceate
//////        //new one if not exists. This is analogous to EJB transaction
//////        //attribute with the same name. This one is default setting of
//////        //transaction definition and typically defines transaction
//////        //synchronization scope.
//////        defaultTransactionDefinition.setPropagationBehavior(
//////                TransactionDefinition.PROPAGATION_REQUIRED);
//////        //Retrieve transaction status information and to programmatically
//////        //request roll-back
//////        TransactionStatus status = platformTransactionManager.
//////                getTransaction(defaultTransactionDefinition);
//////        int value;
//////        try {
//////            String inserQuery = "INSERT INTO person(First_Name, "
//////                    + "Last_Name, Street_Name, City, State, Country) "
//////                    + "VALUES(?, ?, ?, ?, ?, ?)";
//////            Object[] params = new Object[] { person.getFirstName(),
//////                    person.getLastName(),
//////                    person.getStreet(), person.getCity(),
//////                    person.getState(), person.getCountry() };
//////            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,
//////                    Types.VARCHAR, Types.VARCHAR,
//////                    Types.VARCHAR, Types.VARCHAR };
//////            value = jdbcTemplate.update(inserQuery, params, types);
//////            //commit transaction
//////            platformTransactionManager.commit(status);
//////            System.out.println("\nPerson inserted to the table");
//////
//////        } catch (Exception e) {
//////            e.printStackTrace();
//////            platformTransactionManager.rollback(status);
//////            throw e;
//////        }
////
////        return 1;
////    }
//
//    @PutMapping("{id}")
//    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
//
//
//        System.out.println(Integer.decode(id));
//        jdbcTemplate.update("UPDATE users SET name = ?, surname = ? WHERE id = ?",new Object[] { message.get("name"),message.get("surname"), Integer.decode(id) } );
//
//        return message;
//    }
//
//
//
//
////    private Map<String, String> getMessage(@PathVariable String id) {
////        return messages.stream()
////                .filter(message -> message.get("id").equals(id))
////                .findFirst()
////                .orElseThrow();
////  }
////    @PutMapping("{id}")
////    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
////        Map<String, String> messageFromDb = getMessage(id);
////
////        messageFromDb.putAll(message);
////        messageFromDb.put("id", id);
////
////        return messageFromDb;
////    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable String id) {
//        jdbcTemplate.update("delete from users where id = ?", Integer.decode(id));
//
//    }
}