package ua.training.model.entity;

import lombok.Data;

public @Data class Ticket {
    private Seance seance;
    private User user;
    private int row;
    private int seat;
}
