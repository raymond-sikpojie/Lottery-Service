package omogbare.sikpojie.lottery.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemNotFound extends LogicalError{
    String message;
}
