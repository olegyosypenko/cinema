package ua.training.model.dao;

import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public interface SeanceDao extends AutoCloseable {
    void createSeance(Seance seance);
    List<Seance> getSeancesByFilmId(int id);
    List<SeanceDto> getSeancesByDate(Date date);

    void getSeanceParametersById(int id);

    void deleteSeanceById(int id);

    SeanceDto getSeanceById(int seanceId);
    void close();

}
