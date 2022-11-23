package omogbare.sikpojie.lottery.domain;


import lombok.Value;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;

@Value
public class DrawnTicket extends Ticket{
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;

}
