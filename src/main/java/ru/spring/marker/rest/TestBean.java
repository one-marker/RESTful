package ru.spring.marker.rest;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
    String name = "TestBean";

    public TestBean() { }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
