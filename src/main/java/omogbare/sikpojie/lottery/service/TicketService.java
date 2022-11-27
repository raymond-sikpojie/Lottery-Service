package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.exceptions.InvalidTicketType;
import omogbare.sikpojie.lottery.exceptions.ItemNotFound;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.response.RetrieveStatusResponse;

import java.util.List;

public interface TicketService {

    OpenTicket createTicket(TicketRequest ticketRequest) throws FailedConversion; // POST TICKET

    List<Ticket> getAllTickets(); // GET ALL TICKETS

    Ticket getTicketById(Long id) throws ItemNotFound; // GET ONE TICKET

//    Ticket ammendLines(OpenTicket ticket, TicketRequest request);

  Ticket ammendLines(Long id, TicketRequest ticketRequest) throws InvalidTicketType, ItemNotFound; // PUT - EDIT TICKET LINES

    RetrieveStatusResponse retrieveTicketStatus(Long id) throws ItemNotFound; // PUT - RETRIEVE TICKET STATUS

}
