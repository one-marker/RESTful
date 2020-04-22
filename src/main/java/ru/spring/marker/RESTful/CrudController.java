package ru.spring.marker.RESTful;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("message")
class CrudController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};

    @GetMapping
    public List<Map<String, Object>> list() {



        List results = jdbcTemplate.queryForList("SELECT * FROM users");


//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
        //return messages;
        return jdbcTemplate.queryForList("SELECT * FROM users");
    }

    @GetMapping("{id}")
    public List<Map<String, Object>> getOne(@PathVariable String id) {
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE id = ?", new Object[] { "id" });
    }



    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {

        jdbcTemplate.update("INSERT INTO users(name, surname) VALUES (?,?)", message.values().toArray());
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {


        System.out.println(Integer.decode(id));
        jdbcTemplate.update("UPDATE users SET name = ?, surname = ? WHERE id = ?",new Object[] { message.get("name"),message.get("surname"), Integer.decode(id) } );

        return message;
    }




    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow();

    }
//    @PutMapping("{id}")
//    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
//        Map<String, String> messageFromDb = getMessage(id);
//
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id);
//
//        return messageFromDb;
//    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        jdbcTemplate.update("delete from users where id = ?", Integer.decode(id));

    }
}