package omogbare.sikpojie.lottery.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationError {
    private HttpStatus status;
    List<ErrorMessage> errors;


}
