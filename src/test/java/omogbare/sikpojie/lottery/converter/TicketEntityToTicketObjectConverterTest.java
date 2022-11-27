package omogbare.sikpojie.lottery.converter;

import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.lottery.OutcomeGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static omogbare.sikpojie.lottery.helpers.EntityFactory.createTicketEntity;
import static org.junit.jupiter.api.Assertions.*;

class TicketEntityToTicketObjectConverterComponentTest {

    OutcomeGenerator outcomeGenerator = new OutcomeGenerator();

    TicketEntityToTicketObjectConverter converter = new TicketEntityToTicketObjectConverter(outcomeGenerator);

    @Test
    void testTicketWithEmptyRaffleNumbersGetConverted(){
        TicketEntity entity = createTicketEntity();

        RaffleNumberEntity raffleNumberEntity = new RaffleNumberEntity(entity, List.of(1,2,3));
        entity.setRaffleEntities(List.of(raffleNumberEntity));
        Ticket ticket = converter.convert(entity);

    }

}