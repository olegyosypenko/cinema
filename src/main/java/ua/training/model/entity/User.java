package ua.training.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public @Data class User {
    public enum Role {UNKNOWN, ADMIN, USER}
    private int id;
    private String login;
    private String password;
    private Role role;
    private long money;
    private String firstName;
    private String lastName;
    private List<Film> films;
}
