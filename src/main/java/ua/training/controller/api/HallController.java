package ua.training.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.training.model.data.HallRepository;
import ua.training.model.entity.Hall;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HallController {
    private final HallRepository hallRepository;

    public HallController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @GetMapping(value = "halls")
    public Iterable<Hall> getHalls() {
        return hallRepository.findAll();
    }

    @PostMapping(value = "halls")
    public void postHall(@RequestBody Hall hall, HttpServletResponse response) {
        hallRepository.save(hall);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
