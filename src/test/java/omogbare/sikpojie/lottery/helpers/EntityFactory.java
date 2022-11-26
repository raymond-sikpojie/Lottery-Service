package omogbare.sikpojie.lottery.helpers;

import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;

import java.time.Instant;
import java.util.Arrays;

public class EntityFactory {

    public  static OpenTicket openTicket = new OpenTicket(
                        new Id(1L),
                        new CreatedAt(Instant.ofEpochMilli(1669384874L)),
                        new ModifiedAt(Instant.ofEpochMilli(1669384874L)),
                        Arrays.asList(
                                new Outcome(1),
                                new Outcome(10),
                                new Outcome(11)
                        )
    );

    public static  TicketEntity ticketEntity = createTicketEntity();
    public static TicketEntity createTicketEntity(){
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(1L);
        ticketEntity.setCreated(Instant.ofEpochMilli(1669384874L));
        ticketEntity.setModified(Instant.ofEpochMilli(1669384874L));
        ticketEntity.setChecked(false);
        return ticketEntity;
    }

    public static RaffleNumbers createRaffleNumber(){
        return new RaffleNumbers(Arrays.asList(1,2,3));
    }

}
