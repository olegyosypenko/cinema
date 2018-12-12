package ua.training.model.entity;

import lombok.Data;

import java.util.List;

public @Data class User {
    public enum Role {UNKNOWN, ADMIN, USER}
    private int id;
    private String login;
    private String password;
    private Role role;
    private long money;
    private String firstName;
    private String secondName;
    private List<Film> films;
}
