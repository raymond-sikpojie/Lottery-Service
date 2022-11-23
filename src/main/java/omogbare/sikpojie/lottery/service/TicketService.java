package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    TicketEntity save(TicketEntity ticket); // POST TICKET
    List<TicketEntity> getAllTickets(); // GET ALL TICKETS
    TicketEntity getTicketById(); // GET ONE TICKET
    TicketEntity editTicketLines(); // PUT - EDIT TICKET LINES
    String editTicketStatus(); // PUT - RETRIEVE TICKET STATUS

}
