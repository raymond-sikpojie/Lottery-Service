package omogbare.sikpojie.lottery.domain.tickets;

import lombok.*;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;

import java.util.List;


@Data
public abstract class Ticket {
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;
    List<RaffleNumbers> raffleLines;
    List<Outcome> lines;
}
