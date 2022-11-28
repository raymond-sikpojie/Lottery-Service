package omogbare.sikpojie.lottery.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public TicketResponse(Long id, Instant createdAt, Instant modifiedAt, List<Outcome> lines) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.lines = lines;
    }

    @JsonProperty("created_at")
    Instant createdAt;

    @JsonProperty("modified_at")
    Instant modifiedAt;

    @JsonProperty("lines")
    List<Outcome> lines;
}
