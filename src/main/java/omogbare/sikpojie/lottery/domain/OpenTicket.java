package omogbare.sikpojie.lottery.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;


@Value
public class OpenTicket extends Ticket{
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;


}
