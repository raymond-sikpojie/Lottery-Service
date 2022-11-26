package omogbare.sikpojie.lottery.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailedConversion extends LogicalError{
    String message;
}
