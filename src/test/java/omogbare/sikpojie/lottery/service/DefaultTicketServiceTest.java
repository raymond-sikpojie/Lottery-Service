package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.converter.TicketEntityToTicketObjectConverter;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbersGenerator;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.repository.RaffleNumberRepository;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static omogbare.sikpojie.lottery.helpers.EntityFactory.createRaffleNumber;
import static omogbare.sikpojie.lottery.helpers.EntityFactory.openTicket;
import static omogbare.sikpojie.lottery.helpers.EntityFactory.ticketEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class DefaultTicketServiceTest {



    TicketEntityToTicketObjectConverter ticketEntityConverterMock = mock(TicketEntityToTicketObjectConverter.class);

    TicketRepository ticketRepositoryMock = mock(TicketRepository.class);

    RaffleNumbersGenerator raffleNumbersGeneratorMock = mock(RaffleNumbersGenerator.class);

    RaffleNumberRepository raffleNumberRepositoryMock = mock(RaffleNumberRepository.class);

    DefaultTicketService defaultTicketService = new DefaultTicketService(
            ticketRepositoryMock,
            raffleNumbersGeneratorMock,
            raffleNumberRepositoryMock,
            ticketEntityConverterMock
    );

    @BeforeEach
    void init() throws FailedConversion{
        when(ticketRepositoryMock.save(any())).thenReturn(ticketEntity);
        when(raffleNumbersGeneratorMock.create()).thenReturn(createRaffleNumber());
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setNumberOfLines(1);
        when(ticketEntityConverterMock.convertToOpenTicket(any())).thenReturn(openTicket);
        defaultTicketService.createTicket(ticketRequest);
    }

    @DisplayName("Test to know if TicketEntityToDomainTicketObjectConverter")
    @Test
    public void testCallToConvertToOpenTicket() throws FailedConversion {
        verify(ticketEntityConverterMock, times(1)).convertToOpenTicket(any());
    }

    @DisplayName("Test to know if RaffleNumberRepository is called")
    @Test
    public void testCallToRaffleNumberRepository() throws FailedConversion {
        verify(raffleNumberRepositoryMock, times(1)).saveAll(any());
    }

    @DisplayName("Test to know if RaffleNumberGenerator is called")
    @Test
    public void testCallToRaffleNumberGenerator() throws FailedConversion {
        verify(raffleNumbersGeneratorMock, times(1)).create();
    }

    @DisplayName("Test to know if TicketEntityConverter is called")
    @Test
    public void testCallToTicketEntityConverterMock() throws FailedConversion {
        verify(ticketRepositoryMock, times(1)).save(any());
    }
}