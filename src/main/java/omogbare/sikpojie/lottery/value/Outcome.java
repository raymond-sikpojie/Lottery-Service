package omogbare.sikpojie.lottery.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Outcome {
    @JsonProperty("outcome")
    Integer number;

}


