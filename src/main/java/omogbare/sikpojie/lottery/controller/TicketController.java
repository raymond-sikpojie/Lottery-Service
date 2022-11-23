package omogbare.sikpojie.lottery.controller;

import omogbare.sikpojie.lottery.domain.OpenTicket;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.service.TicketService;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/v1")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public TicketEntity save() {
        return ticketService.save(new TicketEntity());
    }

    @GetMapping
    public String getItem() {

//    public String getEntity() {
//        OpenTicket ticket = new OpenTicket(
//                new Id(1),
//                new CreatedAt(Instant.ofEpochMilli(1669150633L)),
//                new ModifiedAt(Instant.ofEpochMilli(1669150633L))
//        );
//        System.out.println("Here ------------------------>");
//        System.out.println(ticket);
        return "this is a ticket";
    }
}
