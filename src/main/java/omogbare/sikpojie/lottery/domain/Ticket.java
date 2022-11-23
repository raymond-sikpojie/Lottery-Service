package omogbare.sikpojie.lottery.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Ticket {
    Id id;
    CreatedAt createdAt;
    ModifiedAt modifiedAt;
}
