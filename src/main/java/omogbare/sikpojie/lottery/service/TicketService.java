package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.request.TicketRequest;

import java.util.List;

public interface TicketService {

    OpenTicket createTicket(TicketRequest ticketRequest) throws FailedConversion; // POST TICKET

    List<Ticket> getAllTickets(); // GET ALL TICKETS

    Ticket getTicketById(Long id); // GET ONE TICKET

    Ticket ammendLines(OpenTicket ticket, TicketRequest request);

//    Ticket ammendLines(Long id, TicketRequest ticketRequest) throws FailedConversion; // PUT - EDIT TICKET LINES

    String retrieveTicketStatus(Long id); // PUT - RETRIEVE TICKET STATUS

}
