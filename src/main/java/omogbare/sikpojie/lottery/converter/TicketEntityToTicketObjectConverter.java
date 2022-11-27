package omogbare.sikpojie.lottery.converter;

import com.google.common.collect.ImmutableList;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.domain.tickets.ClosedTicket;
import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.lottery.OutcomeGenerator;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TicketEntityToTicketObjectConverter {

    OutcomeGenerator outcomeGenerator;

    public TicketEntityToTicketObjectConverter(@Autowired OutcomeGenerator outcomeGenerator) {
        this.outcomeGenerator = outcomeGenerator;
    }

    /**
     *This takes in a ticket entity and determines what type of ticket it returns. An open or closed ticket.
     */

    public Ticket convert(TicketEntity ticketEntity){
        List<Outcome> outcomes = ticketEntity.getRaffleEntities().stream()
                .map(raffleNumberEntity -> raffleNumberEntity.getNumbers())
                .map(num -> new RaffleNumbers(num))

                .map(raffleNumbers -> outcomeGenerator.create(raffleNumbers))
                .collect(Collectors.toList());

        Id id = new Id(ticketEntity.getId());
        CreatedAt createdAt = new CreatedAt(ticketEntity.getCreated());
        ModifiedAt modifiedAt = new ModifiedAt(ticketEntity.getModified());

        if( ticketEntity.getChecked() ){
            ImmutableList<Outcome> immutableOutcome = ImmutableList.copyOf(outcomes);
            return new ClosedTicket(id, createdAt, modifiedAt, immutableOutcome);
        } else{
            return  new OpenTicket(id, createdAt, modifiedAt, outcomes);
        }
    }

    public OpenTicket convertToOpenTicket(TicketEntity ticketEntity) throws FailedConversion {
        if (!ticketEntity.getChecked()){
            Stream<RaffleNumbers> raffleNumbers = ticketEntity.getRaffleEntities().stream()
                    .map(raffleNumberEntity -> raffleNumberEntity.getNumbers())
                    .map(num -> new RaffleNumbers(num));

            List<Outcome> outcomes = raffleNumbers.map(raffleNumber -> outcomeGenerator.create(raffleNumber))
                    .collect(Collectors.toList());

            Id id = new Id(ticketEntity.getId());
            CreatedAt createdAt = new CreatedAt(ticketEntity.getCreated());
            ModifiedAt modifiedAt = new ModifiedAt(ticketEntity.getModified());

            return new OpenTicket(id, createdAt, modifiedAt, outcomes);


        }

        throw new FailedConversion("Cannot convert Ticket entity to open ticket");

    }
}
