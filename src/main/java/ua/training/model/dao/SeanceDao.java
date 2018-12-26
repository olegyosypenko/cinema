package ua.training.model.dao;

import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public interface SeanceDao {
    void createSeance(Seance seance);
    List<Seance> getSeancesByFilmId(int id);
    List<Seance> getSeancesByDate(Date date);
    void deleteSeanceById(int id);
}
