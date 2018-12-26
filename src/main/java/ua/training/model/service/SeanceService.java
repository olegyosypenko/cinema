package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.mysql.MySqlDaoFactory;
import ua.training.model.dao.mysql.SeanceMySqlDao;
import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public class SeanceService {
    private DaoFactory daoFactory = new MySqlDaoFactory();
    private SeanceDao seanceDao = daoFactory.createSeanceDao();

    public void createSeance(Seance seance) {
        seanceDao.createSeance(seance);
    }
    public List<Seance> getSeancesByDate(Date date) {
        return seanceDao.getSeancesByDate(date);
    }
    public List<Seance> getSeancesByFilmId(int id) {
        return seanceDao.getSeancesByFilmId(id);
    }
}