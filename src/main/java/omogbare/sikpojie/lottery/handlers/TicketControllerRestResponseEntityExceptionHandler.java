package omogbare.sikpojie.lottery.handlers;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class TicketControllerRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {



    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
//        List<ErrorMessage> messages = ex.getBindingResult().
//                getAllErrors()
//                .stream().map(error -> new ErrorMessage(error.getDefaultMessage()))
//                .collect(Collectors.toList());
        List<ObjectError> errorList = ex.getAllErrors();
        List<ErrorMessage> messages  = new ArrayList<>();

         for(ObjectError error: errorList) {
            ErrorMessage msg = new ErrorMessage(error.getDefaultMessage());
            messages.add(msg);
         }

        return handleExceptionInternal(ex, new ValidationError(HttpStatus.BAD_REQUEST,messages), headers, HttpStatus.BAD_REQUEST, request);

    }

}
