package ua.training.model.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.training.model.entity.Hall;

@Repository
public interface HallRepository extends CrudRepository<Hall, Integer> {

}
