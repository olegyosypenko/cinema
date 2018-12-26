package ua.training.model.entity;

import lombok.Data;

import java.sql.Date;


public @Data class Seance {
    private int id;
    private Date startTime;
    private Date endTime;
    private Hall hall;
    private Film film;
    private int price;
    private int moneyCollected;
}
