package omogbare.sikpojie.lottery.controller;

import omogbare.sikpojie.lottery.converter.TicketEntityToTicketObjectConverter;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.response.TicketResponse;
import omogbare.sikpojie.lottery.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static omogbare.sikpojie.lottery.helpers.EntityFactory.ticketEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TicketControllerTest {

    TicketService ticketServiceMock = mock(TicketService.class);

    TicketRepository ticketRepositoryMock = mock(TicketRepository.class);

    TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverterMock = mock(TicketEntityToTicketObjectConverter.class);


    @BeforeEach
    void init() throws FailedConversion {
//        when(ticketRepositoryMock.save(any())).thenReturn(ticketEntity);
//        TicketResponse ticketResponse = new TicketResponse(any());
//        when(ticketServiceMock.createTicket(any())).thenReturn(ticketResponse);
    }

    @Test
    void createTicket() {
    }

    @Test
    void getAllTickets() {
    }

    @Test
    void getTicketById() {
    }

    @Test
    void ammendLines() {
    }

    @Test
    void retrieveTicketStatus() {
    }
}