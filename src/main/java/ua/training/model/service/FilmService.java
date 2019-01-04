package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dao.mysql.FilmMySqlDao;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;

import javax.xml.ws.Service;
import java.sql.SQLException;
import java.util.List;

public class FilmService implements AutoCloseable {
    private Logger logger = Logger.getLogger(FilmService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private Transaction transaction = daoFactory.getTransaction();

    public List<Film> getAllFilms() {
        FilmDao filmDao = daoFactory.createFilmDao();
        return filmDao.getAllFilms();

    }
    public void createFilm(FilmDto film) {
        FilmDao filmDao = daoFactory.createFilmDao();
        filmDao.createFilm(film);
    }

    public Film getFilmById(int filmId) {
        FilmDao filmDao = daoFactory.createFilmDao();
        return filmDao.getFilmById(filmId);
    }

    public void close() {
        transaction.close();
    }

    public void deleteFilmById(int id) {
        SeanceDao seanceDao = daoFactory.createSeanceDao();
        FilmDao filmDao = daoFactory.createFilmDao();
        transaction.startTransaction();
        transaction.setSerializable();
        List<Seance> list = seanceDao.getSeancesByFilmId(id);
        if (list.size() > 0) {
            logger.info("List of seances is not empty!");
            transaction.rollback();
            throw new ServiceException("Try to delete film before seances");
        } else {
            try {
                filmDao.deleteFilmById(id);
                transaction.commit();
            } catch(DaoException e) {
                transaction.rollback();
                throw new ServiceException("Cannot delete film", e);
            }
        }
    }
}
