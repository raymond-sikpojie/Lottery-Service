package omogbare.sikpojie.lottery.domain.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public abstract class Ticket {
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;
    List<Outcome> lines;
}
