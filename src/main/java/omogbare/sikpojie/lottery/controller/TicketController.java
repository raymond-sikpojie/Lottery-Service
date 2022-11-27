package omogbare.sikpojie.lottery.controller;

import omogbare.sikpojie.lottery.converter.TicketEntityToTicketObjectConverter;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.exceptions.InvalidTicketType;
import omogbare.sikpojie.lottery.exceptions.ItemNotFound;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.response.RetrieveStatusResponse;
import omogbare.sikpojie.lottery.response.TicketResponse;
import omogbare.sikpojie.lottery.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class TicketController {

    private TicketService ticketService;

    TicketRepository ticketRepository;

    TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverter;

    public TicketController(@Autowired TicketService ticketService,
                            @Autowired TicketRepository ticketRepository,
                            @Autowired TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverter) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.ticketEntityToTicketObjectConverter = ticketEntityToTicketObjectConverter;
    }

    @PostMapping("/ticket")
    public TicketResponse createTicket(@Valid @RequestBody TicketRequest ticketRequest) throws FailedConversion {
        Ticket ticket = ticketService.createTicket(ticketRequest);
        TicketResponse ticketResponse = new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
                );
        return ticketResponse;

    }

    @GetMapping("/ticket")
    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return tickets.stream().map(ticket -> new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
        )).collect(Collectors.toList());
    }

    @GetMapping("/ticket/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) throws ItemNotFound {
        Ticket ticket = ticketService.getTicketById(id);

        return new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
        );
    }

    @PutMapping("/ticket/{id}")
    public TicketResponse ammendLines(@PathVariable Long id,
                                           @RequestBody TicketRequest ticketRequest) throws InvalidTicketType, ItemNotFound {


        Ticket ticket = ticketService.ammendLines(id, ticketRequest);
        return new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getLines()
        );


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

    @PutMapping("/status/{id}")
    public RetrieveStatusResponse retrieveTicketStatus(@PathVariable Long id) throws ItemNotFound {

        return ticketService.retrieveTicketStatus(id);
    };


}
