package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.HallDao;
import ua.training.model.entity.Hall;

public class HallService {

    DaoFactory daoFactory = DaoFactory.getInstance();
    public Hall getHall(int hallId) {
        try (HallDao hallDao = daoFactory.createHallDao()) {
            return hallDao.getHallById(hallId);
        }

    }
}
