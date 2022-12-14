package omogbare.sikpojie.lottery.ErrorHandlers;

import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.exceptions.LogicalError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Catches validation errors where the @Validation annotation is used.
 */
@ControllerAdvice
public class TicketControllerRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<ErrorMessage> messages = new ArrayList<>();

        for (ObjectError error : errorList) {
            ErrorMessage msg = new ErrorMessage(error.getDefaultMessage());
            messages.add(msg);
        }

        return handleExceptionInternal(exception, new ValidationError(HttpStatus.BAD_REQUEST, messages), headers, HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(LogicalError.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage genericExceptionHandler(Exception exception) {
        Exception e = exception;
        return new ErrorMessage(exception.getMessage());
    }

}
