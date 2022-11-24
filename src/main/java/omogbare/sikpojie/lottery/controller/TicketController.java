package omogbare.sikpojie.lottery.controller;

import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public OpenTicket save(@Valid @RequestBody TicketRequest ticketRequest) {
        OpenTicket ticket = ticketService.save(ticketRequest);
        return ticket;

    }

    @GetMapping
    public String getItem() {

//    public String getEntity() {
//        UnCheckedTicket ticket = new UnCheckedTicket(
//                new Id(1),
//                new CreatedAt(Instant.ofEpochMilli(1669150633L)),
//                new ModifiedAt(Instant.ofEpochMilli(1669150633L))
//        );
//        System.out.println("Here ------------------------>");
//        System.out.println(ticket);
        return "this is a ticket";
    }



}
