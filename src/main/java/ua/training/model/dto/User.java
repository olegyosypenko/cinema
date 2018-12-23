package ua.training.model.dto;

import lombok.Data;
import ua.training.model.entity.Film;

import java.util.List;
public @Data class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String firstNameEN;
    private String lastNameEN;
}
