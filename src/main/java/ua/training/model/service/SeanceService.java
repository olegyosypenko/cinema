package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.mysql.MySqlDaoFactory;
import ua.training.model.dto.SeanceDto;
import ua.training.model.entity.Hall;
import ua.training.model.entity.Seance;

import java.sql.Date;
import java.util.List;

public class SeanceService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createSeance(Seance seance) {
        try (SeanceDao seanceDao = daoFactory.createSeanceDao()) {
            seanceDao.createSeance(seance);
        }
    }
    public List<ua.training.model.dto.SeanceDto> getSeancesByDate(Date date) {
        try (SeanceDao seanceDao = daoFactory.createSeanceDao()) {
            return seanceDao.getSeancesByDate(date);
        }
    }
    public List<Seance> getSeancesByFilmId(int id) {
        try (SeanceDao seanceDao = daoFactory.createSeanceDao()) {
            return seanceDao.getSeancesByFilmId(id);
        }
    }

    public SeanceDto getSeanceDtoById(int seanceId) {
        try (SeanceDao seanceDao = daoFactory.createSeanceDao()) {
            return seanceDao.getSeanceById(seanceId);
        }
    }

    public Seance getSeanceById(int seanceId) {
        Seance seance = new Seance();
        SeanceDto seanceDto = getSeanceDtoById(seanceId);
        Hall hall = new Hall();
        hall.setColumns(seanceDto.getColumns());
        hall.setRows(seanceDto.getRows());
        seance.setId(seanceId);
        seance.setPrice(seanceDto.getPrice());
        seance.setDuration(seanceDto.getDuration());
        seance.setStartTime(seanceDto.getStartTime());
        seance.setHall(hall);
        return seance;
    }
}