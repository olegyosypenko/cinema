package ua.training.model.entity;

import lombok.Data;

import java.util.Date;

public @Data class Seance {
    private int id;
    private Date startTime;
    private int rows;
    private int columns;
    private Film film;
    private long price;
    private long moneyCollected;
}
