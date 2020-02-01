package ua.training.model.dao.mysql.mappers;

import ua.training.model.dto.SeanceDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeanceMapper {
    public static SeanceDto mapToSeanceDto(ResultSet resultSet) throws SQLException {
        SeanceDto seanceDto = new SeanceDto();
        seanceDto.setId(resultSet.getInt(1));
        seanceDto.setStartTime(resultSet.getTimestamp(2));
        seanceDto.setDuration(resultSet.getInt(3));
        seanceDto.setPrice(resultSet.getInt(4));
        seanceDto.setName(resultSet.getString(5));
        seanceDto.setColumns(resultSet.getInt(6));
        seanceDto.setRows(resultSet.getInt(7));
        return seanceDto;
    }
}
