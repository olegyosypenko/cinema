package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.util.UriParser;
import ua.training.model.dao.exceptions.DaoException;
import ua.training.model.dto.SeanceDto;
import ua.training.model.dto.SeanceParametersDto;
import ua.training.model.entity.Hall;
import ua.training.model.entity.Ticket;
import ua.training.model.service.SeanceService;
import ua.training.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BuyTicketsPageCommand extends Command {
    private Logger logger = Logger.getLogger(BuyTicketsPageCommand.class);
    private SeanceService seanceService = new SeanceService();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        try {
            int seanceId = UriParser.getIndexFromUri(request.getRequestURI());
            SeanceDto seanceDto = seanceService.getSeanceDtoById(seanceId);
            List<Ticket> tickets = seanceDto.getTickets();
            Hall hall = new Hall();
            hall.setRows(seanceDto.getRows());
            hall.setColumns(seanceDto.getColumns());
            SeanceParametersDto parameters = createParameters(hall, tickets);
            request.setAttribute("seance", seanceService.getSeanceDtoById(seanceId));
            request.setAttribute("parameters", parameters);
            forward("/WEB-INF/pages/buy-tickets.jsp");
        } catch (DaoException e) {


        }

    }

    // Todo: put this method in another place;
    private SeanceParametersDto createParameters(Hall hall, List<Ticket> tickets) {
        SeanceParametersDto seanceParameters = new SeanceParametersDto();
        seanceParameters.setColumns(hall.getColumns());
        seanceParameters.setRows(hall.getRows());
        int[][] takenPlaces = new int[15][15];
        for (Ticket ticket : tickets) {
            takenPlaces[ticket.getRow() - 1][ticket.getSeat() - 1] = 1;
        }
        seanceParameters.setTaken(takenPlaces);
        return seanceParameters;
    }
}
