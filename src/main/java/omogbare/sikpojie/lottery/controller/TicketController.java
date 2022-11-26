package omogbare.sikpojie.lottery.controller;

import omogbare.sikpojie.lottery.converter.TicketEntityToTicketObjectConverter;
import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.response.TicketResponse;
import omogbare.sikpojie.lottery.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverter;


    @PostMapping
    public TicketResponse createTicket(@Valid @RequestBody TicketRequest ticketRequest) throws FailedConversion {
        Ticket ticket = ticketService.save(ticketRequest);
        TicketResponse ticketResponse = new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
                );
        return ticketResponse;

    }

    @GetMapping
    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return tickets.stream().map(ticket -> new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        TicketResponse ticketResponse = new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
        );
        return ticketResponse;
    }

    /**
     * Pull the entity from db, throw it into the class converter
     */
    @PutMapping("/{id}")
    public TicketResponse ammendLines(@PathVariable Long id,
                                           @RequestBody TicketRequest ticketRequest) throws FailedConversion {

        Ticket ticket = ticketService.ammendLines(id, ticketRequest);
        TicketResponse ticketResponse = new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
        );
        return ticketResponse;
//        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);
//
//        TicketEntity entity = ticketEntity.orElseThrow(IllegalArgumentException::new);
//
//        Ticket ticket = ticketEntityToTicketObjectConverter.convert(entity);
//
//
//        if( ticket instanceof OpenTicket){
//            ticketService.ammendLines((OpenTicket) ticket, ticketRequest);
//
//        }
//
//        return new TicketResponse(
//                ticket.getId().getValue(),
//                ticket.getCreatedAt().getValue(),
//                ticket.getModifiedAt().getValue(),
//                ticket.getLines()
//        );
    }

}
