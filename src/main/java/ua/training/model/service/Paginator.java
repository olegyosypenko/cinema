package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.FilmDao;
import ua.training.model.dao.mysql.MySqlDaoFactory;
import ua.training.model.entity.Film;

import java.util.ArrayList;
import java.util.List;

public class Paginator {
    private DaoFactory daoFactory = new MySqlDaoFactory();




    public List<Film> getFilmsPart(int i) {
        List<Film> films = new FilmDao() {
            @Override
            public Film getFilmById(int id) {
                return null;
            }

            @Override
            public List<Film> getAllFilms() {
                List<Film> list = new ArrayList<>();
                for (int i = 0; i < 54; i++) {
                    list.add(null);
                }
                return list;
            }

            @Override
            public void createFilm(Film film) {

            }
        }.getAllFilms();
        int numberOfFilms = 10;
        if (i < 0) {
            i = 0;
        } else if (films.size() / 10 <= i) {
            i = films.size() / 10;
            numberOfFilms = films.size() - i * 10;
        }
        
        return films.subList(i * 10, i * 10 + numberOfFilms);
    }
}
