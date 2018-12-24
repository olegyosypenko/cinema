package ua.training.model.dto;

import lombok.Data;

public @Data class FilmDto {
    private String name;
    private String nameEN;
    private String genre;
    private String genreEN;
    private String director;
    private String directorEN;
    private float rate;
    private String description;
    private String descriptionEN;
    private String imageLink;
    private String imageLinkEN;
}