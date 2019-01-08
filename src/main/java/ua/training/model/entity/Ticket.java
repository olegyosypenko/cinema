package ua.training.model.entity;


public class Ticket {
    private Seance seance;
    private User user;
    private int row;
    private int seat;

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }


    @Override
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
