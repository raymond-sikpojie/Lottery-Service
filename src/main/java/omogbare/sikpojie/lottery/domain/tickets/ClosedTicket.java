package omogbare.sikpojie.lottery.domain.tickets;

import com.google.common.collect.ImmutableList;
import lombok.*;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;

@Value
@EqualsAndHashCode(callSuper = true)
public class ClosedTicket extends Ticket {
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;
    ImmutableList<RaffleNumbers> raffleLines;
    ImmutableList<Outcome> lines;
}
