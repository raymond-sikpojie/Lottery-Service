package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTicketService implements TicketService{

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    RaffleNumberService raffleNumberService;


    @Override
    public TicketEntity save(TicketEntity ticket) {
        ticketRepository.save(ticket);
        RaffleNumberEntity raffleNumber = new RaffleNumberEntity();
        raffleNumber.setTicketEntity(ticket);
        raffleNumberService.saveRaffleNumber(raffleNumber);
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
