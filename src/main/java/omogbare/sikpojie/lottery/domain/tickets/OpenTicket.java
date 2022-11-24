package omogbare.sikpojie.lottery.domain.tickets;


import lombok.*;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;

import java.util.List;




@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OpenTicket extends Ticket {
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;
    List<Outcome> lines;
}
