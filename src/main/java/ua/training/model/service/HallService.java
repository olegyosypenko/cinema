package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.HallDao;
import ua.training.model.entity.Hall;

public class HallService implements AutoCloseable {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public Hall getHall(int hallId) {
        HallDao hallDao = daoFactory.createHallDao();
        return hallDao.getHallById(hallId);
    }


    public void close() {
        daoFactory.getTransaction().close();
    }
}
