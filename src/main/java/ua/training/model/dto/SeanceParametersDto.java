package ua.training.model.dto;

public class SeanceParametersDto {
    private int rows;
    private int columns;
    private int[][] taken;


    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][] getTaken() {
        return taken;
    }

    public void setTaken(int[][] taken) {
        this.taken = taken;
    }


}