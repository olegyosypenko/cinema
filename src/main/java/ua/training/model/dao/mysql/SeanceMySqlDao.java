package ua.training.model.dao.mysql;

import ua.training.model.dao.SeanceDao;
import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public class SeanceMySqlDao implements SeanceDao {
    @Override
    public List<Seance> getSeancesByFilmId(int id) {
        return null;
    }

    @Override
    public List<Seance> getSeancesByDate(Date date) {
        return null;
    }

    @Override
    public void deleteSeanceById(int id) {

    }
}
