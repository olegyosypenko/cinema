package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.mysql.MySqlDaoFactory;
import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public class SeanceService {
    private DaoFactory daoFactory = new MySqlDaoFactory();

    public List<Seance> getSeancesByDate(Date date) {
        return daoFactory.createSeanceDao().getSeancesByDate(date);
    }
    public List<Seance> getSeancesByFilmId(int id) {
        return daoFactory.createSeanceDao().getSeancesByFilmId(id);
    }
}