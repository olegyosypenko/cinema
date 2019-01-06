package ua.training.model.dto;

import lombok.Data;
import ua.training.model.entity.Ticket;

import java.sql.Timestamp;
import java.util.List;

public @Data class SeanceDto {
    private int id;
    private Timestamp startTime;
    private int duration;
    private int price;
    private String name;
    private int columns;
    private int rows;
    private List<Ticket> tickets;
}
