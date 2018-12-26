package ua.training.model.dto;

import lombok.Data;

import java.sql.Timestamp;

public @Data class SeanceDto {
    private int id;
    private Timestamp startTime;
    private int duration;
    private int price;
    private String name;
    private int columns;
    private int rows;
}
