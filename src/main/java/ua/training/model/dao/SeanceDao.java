package ua.training.model.dao;

import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public interface SeanceDao {
    void createSeance(Seance seance);
    List<Seance> getSeancesByFilmId(int id);
    List<SeanceDto> getSeancesByDate(Date date);
    void deleteSeanceById(int id);
    SeanceDto getSeanceDtoById(int seanceId);
}
