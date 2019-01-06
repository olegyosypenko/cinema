package ua.training.model.entity;

import lombok.Data;

public @Data class Ticket {
    private Seance seance;
    private User user;
    private int row;
    private int seat;


    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) that;
        return ticket.getSeat() == seat && ticket.getRow() == row && ticket.getSeance().getId() == seance.getId();
    }

    @Override
    public int hashCode() {
        int p = 17;
        int q = 37;
        int result = p;
        result = result * q + row;
        result = result * q + seat;
        result = result * q + seance.getId();
        return result;
    }
}
