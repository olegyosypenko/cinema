package ua.training.model.dto;

import lombok.Data;

import java.util.List;

public @Data class Film {
    private String name;
    private String nameEN;
    private String genre;
    private String genreEN;
    private String director;
    private String directorEN;
    private float rate;
    private String description;
    private String descriptionEN;
}