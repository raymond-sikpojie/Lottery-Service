package omogbare.sikpojie.lottery.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class LogicalError extends Exception {
    String message;
}
