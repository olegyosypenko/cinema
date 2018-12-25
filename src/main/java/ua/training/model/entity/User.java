package ua.training.model.entity;

import lombok.Data;

import java.util.List;
public @Data class User {
    private int id;
    private String username;
    private String password;
    private Role role;
    private long money;
    private String firstName;
    private String lastName;
    private List<Film> films;
}
