package omogbare.sikpojie.lottery.value;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
public class ModifiedAt {
    Instant value;
}
