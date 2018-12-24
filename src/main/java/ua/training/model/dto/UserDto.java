package ua.training.model.dto;

import lombok.Data;
public @Data class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String firstNameEN;
    private String lastNameEN;
}
