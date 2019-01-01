package ua.training.model.dao;

import ua.training.model.entity.Hall;

public interface HallDao extends AutoCloseable {

    Hall getHallById(int id);
    void close();

}
