package ua.training.model.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


public @Data class Seance {
    private int id;
    private Timestamp startTime;
    private int duration;
    private Hall hall;
    private Film film;
    private int price;
    private List<Ticket> tickets;
}
