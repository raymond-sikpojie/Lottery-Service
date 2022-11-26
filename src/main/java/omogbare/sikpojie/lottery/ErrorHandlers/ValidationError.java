package omogbare.sikpojie.lottery.ErrorHandlers;

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
