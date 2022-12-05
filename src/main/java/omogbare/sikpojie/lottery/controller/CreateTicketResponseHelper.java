package omogbare.sikpojie.lottery.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.response.TicketResponse;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateTicketResponseHelper extends TicketResponse {

    public TicketResponse create(Ticket ticket) {
        TicketResponse ticketResponse = new TicketResponse(
                ticket.getId().getValue(),
                ticket.getCreatedAt().getValue(),
                ticket.getModifiedAt().getValue(),
                ticket.getRaffleLines(),
                ticket.getLines()
        );
        return ticketResponse;
    }
}
