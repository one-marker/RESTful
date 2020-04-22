package ru.spring.marker.RESTful;

public class User {
    private int id;
    private String name, surname;

    public User(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s', surname='%s']",
                id, name, surname);
    }

    // getters & setters omitted for brevity
}