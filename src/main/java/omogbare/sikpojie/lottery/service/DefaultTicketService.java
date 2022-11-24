package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.domain.raffle.helpers.RaffleFactory;
import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.repository.RaffleNumberRepository;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.value.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService{

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    RaffleNumberService raffleNumberService;
    @Autowired
    RaffleFactory raffleFactory;
    @Autowired
    RaffleNumberRepository raffleNumberRepository;


    @Override
    public OpenTicket save(TicketRequest ticketRequest) {

        //create ticket entity obj and save
         TicketEntity ticketEntity = new TicketEntity();
         ticketRepository.save(ticketEntity);

         //create raffle numbers as a list
         RaffleNumbers raffleNumbers = raffleFactory.create();

         //create ticket entity obj, set its values and save
         RaffleNumberEntity raffleNumberEntity = new RaffleNumberEntity();
         raffleNumberEntity.setTicketEntity(ticketEntity);
         raffleNumberEntity.setNumbers(raffleNumbers.getNumbers());
        raffleNumberRepository.save(raffleNumberEntity);

        //determine outcomes
        List<Outcome> outcomes  = new ArrayList<>();
        outcomes.add( new Outcome(5, OutcomeStatus.UNDETERMINED));
        outcomes.add(new Outcome(10, OutcomeStatus.UNDETERMINED));

        //create a ticket
        OpenTicket ticket = new  OpenTicket(
                new Id(ticketEntity.getId()),
                new CreatedAt(ticketEntity.getCreated()),
                new ModifiedAt(ticketEntity.getModified()),
                outcomes
        );

        return ticket;
    }


    @Override
    public List<TicketEntity> getAllTickets() {
        return null;
    }

    @Override
    public TicketEntity getTicketById() {
        return null;
    }

    @Override
    public TicketEntity editTicketLines() {
        return null;
    }

    @Override
    public String editTicketStatus() {
        return null;
    }
}
