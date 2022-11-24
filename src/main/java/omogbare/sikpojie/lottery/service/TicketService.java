package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.request.TicketRequest;

import java.util.List;

public interface TicketService {

    OpenTicket save(TicketRequest ticketRequest); // POST TICKET
    List<TicketEntity> getAllTickets(); // GET ALL TICKETS
    TicketEntity getTicketById(); // GET ONE TICKET
    TicketEntity editTicketLines(); // PUT - EDIT TICKET LINES
    String editTicketStatus(); // PUT - RETRIEVE TICKET STATUS

}
