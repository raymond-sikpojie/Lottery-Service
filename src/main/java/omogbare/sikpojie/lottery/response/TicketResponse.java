package omogbare.sikpojie.lottery.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.value.Outcome;

import java.time.Instant;
import java.util.List;

//@Builder
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class TicketResponse {
    @JsonProperty("ticket_id")
    Long id;

    @JsonProperty("created_at")
    Instant createdAt;

    @JsonProperty("modified_at")
    Instant modifiedAt;

    @JsonProperty("RaffleLines")
    List<RaffleNumbers> raffleLines;
    @JsonProperty("lines")
    List<Outcome> lines;

    public TicketResponse(Long id, Instant createdAt, Instant modifiedAt, List<RaffleNumbers> raffleLines, List<Outcome> lines) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.raffleLines = raffleLines;
        this.lines = lines;

    }

}
