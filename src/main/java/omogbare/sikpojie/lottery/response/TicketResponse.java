package omogbare.sikpojie.lottery.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import omogbare.sikpojie.lottery.value.Outcome;

import java.time.Instant;
import java.util.List;

//@Builder
@Getter
@Setter
@AllArgsConstructor
public class TicketResponse {
    @JsonProperty("ticket_id")
    Long id;

    @JsonProperty("created_at")
    Instant createdAt;

    @JsonProperty("modified_at")
    Instant modifiedAt;

    @JsonProperty("lines")
    List<Outcome> lines;
}
