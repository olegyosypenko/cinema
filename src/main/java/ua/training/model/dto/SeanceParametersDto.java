package ua.training.model.dto;

import lombok.Data;

@Data
public class SeanceParametersDto {
    private int rows;
    private int columns;
    private int[][] taken;
}