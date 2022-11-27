package omogbare.sikpojie.lottery.request;




import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class TicketRequest {
    @NotNull(message = "Please pass in number of lines for ticket")
    @Min(value = 1)
    @Max(value = 10)
    @JsonProperty("number_of_lines")
    private int numberOfLines;
}
