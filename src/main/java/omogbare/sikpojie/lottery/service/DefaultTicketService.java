package omogbare.sikpojie.lottery.service;

import net.bytebuddy.implementation.bytecode.Throw;
import omogbare.sikpojie.lottery.converter.TicketEntityToTicketObjectConverter;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbersGenerator;
import omogbare.sikpojie.lottery.domain.tickets.ClosedTicket;
import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.lottery.OutcomeGenerator;
import omogbare.sikpojie.lottery.repository.RaffleNumberRepository;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.response.TicketResponse;
import omogbare.sikpojie.lottery.value.CreatedAt;
import omogbare.sikpojie.lottery.value.Id;
import omogbare.sikpojie.lottery.value.ModifiedAt;
import omogbare.sikpojie.lottery.value.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DefaultTicketService implements TicketService {
    TicketRepository ticketRepository;
    RaffleNumbersGenerator raffleNumbersGenerator;
    RaffleNumberRepository raffleNumberRepository;

    TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverter;

    OutcomeGenerator outcomeGenerator;

    public DefaultTicketService(@Autowired TicketRepository ticketRepository,
                                @Autowired RaffleNumbersGenerator raffleNumbersGenerator,
                                @Autowired RaffleNumberRepository raffleNumberRepository,
                                @Autowired TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverter,
                                @Autowired OutcomeGenerator outcomeGenerator) {
        this.ticketRepository = ticketRepository;
        this.raffleNumbersGenerator = raffleNumbersGenerator;
        this.raffleNumberRepository = raffleNumberRepository;
        this.ticketEntityToTicketObjectConverter = ticketEntityToTicketObjectConverter;
        this.outcomeGenerator = outcomeGenerator;
    }

    @Override
    public OpenTicket save(TicketRequest ticketRequest) throws FailedConversion {

        //create ticket entity obj and save
        TicketEntity ticketEntity = new TicketEntity();
        ticketRepository.save(ticketEntity);
//        TicketEntity ticketEntity = ticketRepository.save(new TicketEntity());

        //create raffle numbers as a list by calling the
        // raffleNumbersGenerator.create() method n number of times.
        int numberOfRaffleToCreate = ticketRequest.getNumberOfLines();

        List<RaffleNumbers> raffleNumbers = Stream
                .generate(() -> raffleNumbersGenerator.create())
                .limit(numberOfRaffleToCreate)
                .collect(Collectors.toList());
//        List<RaffleNumbers> raffleNumbers = new ArrayList<>();
//
//        for(int i=0; i<numberOfRaffleToCreate; i++) {
//            raffleNumbers.add(raffleNumbersGenerator.create());
//        }


        List<RaffleNumberEntity> entities = raffleNumbers.stream()
                .map(raffleNumber -> new RaffleNumberEntity(ticketEntity, raffleNumber.getNumbers())
                ).collect(Collectors.toList());

        // save all to db with one db call and return the lines outcome
        raffleNumberRepository.saveAll(entities);


        // set entities
        ticketEntity.setRaffleEntities(entities);

        return ticketEntityToTicketObjectConverter.convertToOpenTicket(ticketEntity);


    }


    @Override
    public List<Ticket> getAllTickets() {
        List<TicketEntity> entities = ticketRepository.findAll();

        return entities.stream().map((ticketEntity) -> ticketEntityToTicketObjectConverter.convert(ticketEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicketById(Long id) {
        TicketEntity ticketEntity = ticketRepository.findById(id).get();
        return ticketEntityToTicketObjectConverter.convert(ticketEntity);
    }


    @Override
    public Ticket ammendLines(Long id, TicketRequest ticketRequest) throws FailedConversion {
        TicketEntity ticketEntity = ticketRepository.findById(id).get();
        if(ticketEntityToTicketObjectConverter.convert(ticketEntity) instanceof ClosedTicket) {
             throw new FailedConversion("Unable to edit. Ticket is closed");
        }

        int numberOfRaffleToCreate = ticketRequest.getNumberOfLines();

        List<RaffleNumbers> raffleNumbers = Stream
                .generate(() -> raffleNumbersGenerator.create())
                .limit(numberOfRaffleToCreate)
                .collect(Collectors.toList());

        List<RaffleNumberEntity> raffleNumberEntities = raffleNumbers.stream()
                .map(raffleNumber -> new RaffleNumberEntity(ticketEntity, raffleNumber.getNumbers()))
                .collect(Collectors.toList());

        raffleNumberRepository.saveAll(raffleNumberEntities);

        ticketEntity.setRaffleEntities(raffleNumberEntities);

        Ticket ticket = ticketEntityToTicketObjectConverter.convert(ticketEntity);
//        List<Outcome> outcomes = raffleNumbers.stream()
//                .map(num -> outcomeGenerator.create(num))
//                .collect(Collectors.toList());
//
//        ticket.getLines().addAll(outcomes);
        return ticket;

    }

//    @Override
//    public Ticket ammendLines(OpenTicket ticket, TicketRequest request) {
//        if(ticket instanceof OpenTicket) {
//
//            TicketEntity ticketEntity = new TicketEntity();
//            ticketEntity.setId(ticket.getId().getValue());
//
//            int numberOfRaffleToCreate = request.getNumberOfLines();
//            List<RaffleNumbers> raffleNumbers = Stream
//                    .generate(() -> raffleNumbersGenerator.create())
//                    .limit(numberOfRaffleToCreate)
//                    .collect(Collectors.toList());
//
//            List<RaffleNumberEntity> raffleNumberEntities = raffleNumbers.stream()
//                    .map(raffleNumber -> new RaffleNumberEntity(ticketEntity, raffleNumber.getNumbers())).collect(Collectors.toList());
//
//            raffleNumberRepository.saveAll(raffleNumberEntities);
//            List<Outcome> outcomes = raffleNumbers.stream()
//                    .map(num -> outcomeGenerator.create(num))
//                    .collect(Collectors.toList());
//
//            ticket.getLines().addAll(outcomes);
//            return ticket;
//
//        } else {
//            throw  new RuntimeException("Ticket is already closed");
//
//        }
//
//    }

    @Override
    public String retrieveTicketStatus(Long id) {
//        TicketEntity ticketEntity = ticketRepository.findById(id).get();
        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);

        TicketEntity entity = ticketEntity.orElseThrow(IllegalArgumentException::new);

        if(entity.getChecked() == false) {
            Boolean initialCheckedValue = entity.getChecked();
            entity.setChecked(true);
            ticketRepository.save(entity);
            Ticket closedTicket = ticketEntityToTicketObjectConverter.convert(entity);
            System.out.println(closedTicket instanceof ClosedTicket);
            return "This ticket is now closed, with an initial checked value of " + initialCheckedValue;

        } else {
            return "This ticked is closed, with a checked value of : " + entity.getChecked();
        }
    }
}
