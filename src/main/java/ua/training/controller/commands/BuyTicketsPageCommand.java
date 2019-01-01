package ua.training.controller.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ua.training.model.dto.SeanceParametersDto;
import ua.training.model.entity.Hall;
import ua.training.model.entity.Ticket;
import ua.training.model.service.HallService;
import ua.training.model.service.SeanceService;
import ua.training.model.service.TicketService;
import java.util.List;

public class BuyTicketsPageCommand extends Command {
    private Logger logger = Logger.getLogger(BuyTicketsPageCommand.class);
    @Override
    public void process() {
        SeanceService seanceService = new SeanceService();
        TicketService ticketService = new TicketService();
        HallService hallService = new HallService();
        String[] requestParts = request.getRequestURI().split("/");
        int seanceId = Integer.parseInt(requestParts[requestParts.length - 1]);
        List<Ticket> tickets = ticketService.getTicketsBySeanceId(seanceId);
        Hall hall = hallService.getHall(1);
        SeanceParametersDto parameters = createParameters(hall, tickets);
        String jsonParameters = objectToJson(parameters);
        request.setAttribute("seance", seanceService.getSeanceDtoById(seanceId));
        request.setAttribute("parameters", jsonParameters);
        forward("/WEB-INF/pages/buy-tickets.jsp");
    }

    private String objectToJson(SeanceParametersDto seanceParameters) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonParameters = "";
        try {
            jsonParameters = mapper.writeValueAsString(seanceParameters);
        } catch (JsonProcessingException e) {
            logger.error("Object to Json conversion", e);
        }
        return jsonParameters;
    }

    // Todo: put this method in another place;
    private SeanceParametersDto createParameters(Hall hall, List<Ticket> tickets) {
        SeanceParametersDto seanceParameters = new SeanceParametersDto();
        seanceParameters.setColumns(hall.getColumns());
        seanceParameters.setColumns(hall.getRows());
        int[][] takenPlaces = new int[15][15];
        for (Ticket ticket : tickets) {
            takenPlaces[ticket.getRow() - 1][ticket.getSeat() - 1] = 1;
        }
        seanceParameters.setTaken(takenPlaces);
        return seanceParameters;
    }
}
