package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.SeanceDao;
import ua.training.model.dao.Transaction;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dto.FilmDto;
import ua.training.model.entity.Film;
import ua.training.model.entity.Seance;

import java.util.List;

public class FilmService {
    private Logger logger = Logger.getLogger(FilmService.class);

    public List<Film> getAllFilms() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            FilmDao filmDao = daoFactory.createFilmDao();
            return filmDao.getAllFilms();
        }
    }

    public void createFilm(FilmDto film) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            FilmDao filmDao = daoFactory.createFilmDao();
            filmDao.createFilm(film);
        }
    }

    public Film getFilmById(int filmId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try (Transaction ignored = daoFactory.getTransaction()) {
            FilmDao filmDao = daoFactory.createFilmDao();
            return filmDao.getFilmById(filmId);
        }
    }

    public void deleteFilmById(int id) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        Transaction transaction = daoFactory.getTransaction();
        try {
            SeanceDao seanceDao = daoFactory.createSeanceDao();
            FilmDao filmDao = daoFactory.createFilmDao();
            transaction.startTransaction();
            transaction.setSerializable();
            List<Seance> list = seanceDao.getSeancesByFilmId(id);
            checkIfFilmHasDependentSeances(transaction, list);
            filmDao.deleteFilmById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Cannot delete film", e);
        } finally {
            transaction.close();
        }
    }

    private void checkIfFilmHasDependentSeances(Transaction transaction, List<Seance> list) {
        if (list.size() > 0) {
            logger.info("List of seances is not empty!");
            transaction.rollback();
            throw new ServiceException("Try to delete film before seances");
        }
    }

    public List<Film> getMostPopularFilms() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        FilmDao filmDao = daoFactory.createFilmDao();
        try (Transaction ignored = daoFactory.getTransaction()) {
            return filmDao.getMostPopularFilms();
        }
    }
}
