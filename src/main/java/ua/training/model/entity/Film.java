package ua.training.model.entity;

import lombok.Data;

import java.util.List;

public @Data class Film {
    private int id;
    private String name;
    private String genre;
    private String director;
    private float rate;
    private String description;
    private String imageLink;
    private List<Seance> seances;
}
