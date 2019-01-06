package ua.training.controller.commands;

import ua.training.controller.util.UriParser;
import ua.training.model.dto.SeanceDto;
import ua.training.model.dto.SeanceParametersDto;
import ua.training.model.entity.Hall;
import ua.training.model.entity.Ticket;
import ua.training.model.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BuyTicketsPageCommand extends Command {
    private SeanceService seanceService = new SeanceService();
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        int seanceId = UriParser.getIndexFromUri(request.getRequestURI());
        SeanceDto seanceDto = seanceService.getSeanceDtoById(seanceId);
        List<Ticket> tickets = seanceDto.getTickets();
        Hall hall = new Hall();
        hall.setRows(seanceDto.getRows());
        hall.setColumns(seanceDto.getColumns());
        SeanceParametersDto parameters = createParameters(hall, tickets);
        request.setAttribute("seance", seanceDto);
        request.setAttribute("parameters", parameters);
        return "/WEB-INF/pages/buy-tickets.jsp";
    }

    private SeanceParametersDto createParameters(Hall hall, List<Ticket> tickets) {
        SeanceParametersDto seanceParameters = new SeanceParametersDto();
        seanceParameters.setColumns(hall.getColumns());
        seanceParameters.setRows(hall.getRows());
        int[][] takenPlaces = new int[hall.getColumns()][hall.getRows()];
        for (Ticket ticket : tickets) {
            takenPlaces[ticket.getRow() - 1][ticket.getSeat() - 1] = 1;
        }
        seanceParameters.setTaken(takenPlaces);
        return seanceParameters;
    }
}
