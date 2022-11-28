package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.converter.TicketEntityToTicketObjectConverter;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbersGenerator;
import omogbare.sikpojie.lottery.domain.tickets.ClosedTicket;
import omogbare.sikpojie.lottery.domain.tickets.OpenTicket;
import omogbare.sikpojie.lottery.domain.tickets.Ticket;
import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.entity.TicketEntity;
import omogbare.sikpojie.lottery.exceptions.FailedConversion;
import omogbare.sikpojie.lottery.exceptions.InvalidTicketType;
import omogbare.sikpojie.lottery.exceptions.ItemNotFound;
import omogbare.sikpojie.lottery.repository.RaffleNumberRepository;
import omogbare.sikpojie.lottery.repository.TicketRepository;
import omogbare.sikpojie.lottery.request.TicketRequest;
import omogbare.sikpojie.lottery.response.RetrieveStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public DefaultTicketService(@Autowired TicketRepository ticketRepository,
                                @Autowired RaffleNumbersGenerator raffleNumbersGenerator,
                                @Autowired RaffleNumberRepository raffleNumberRepository,
                                @Autowired TicketEntityToTicketObjectConverter ticketEntityToTicketObjectConverter) {
        this.ticketRepository = ticketRepository;
        this.raffleNumbersGenerator = raffleNumbersGenerator;
        this.raffleNumberRepository = raffleNumberRepository;
        this.ticketEntityToTicketObjectConverter = ticketEntityToTicketObjectConverter;
    }

    @Override
    public OpenTicket createTicket(TicketRequest ticketRequest) throws FailedConversion {

        TicketEntity ticketEntity = new TicketEntity();
        ticketRepository.save(ticketEntity);

        int numberOfRaffleToCreate = ticketRequest.getNumberOfLines();

        List<RaffleNumbers> raffleNumbers = Stream
                .generate(() -> raffleNumbersGenerator.create())
                .limit(numberOfRaffleToCreate)
                .collect(Collectors.toList());

        List<RaffleNumberEntity> entities = raffleNumbers.stream()
                .map(raffleNumber -> new RaffleNumberEntity(ticketEntity, raffleNumber.getNumbers())
                ).collect(Collectors.toList());

        raffleNumberRepository.saveAll(entities);

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
    public Ticket getTicketById(Long id) throws ItemNotFound {
        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);

        TicketEntity entity = ticketEntity.orElseThrow(() -> new ItemNotFound("This ticket does not exist"));

        return ticketEntityToTicketObjectConverter.convert(entity);
    }


    @Override
    public Ticket ammendLines(Long id, TicketRequest ticketRequest) throws InvalidTicketType, ItemNotFound {

        Optional<TicketEntity> entity = ticketRepository.findById(id);

        TicketEntity ticketEntity = entity.orElseThrow(() -> new ItemNotFound("This ticket does not exist"));

        if(ticketEntityToTicketObjectConverter.convert(ticketEntity) instanceof ClosedTicket) {
             throw new InvalidTicketType("Unable to edit. Ticket is closed");
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
        return ticketEntityToTicketObjectConverter.convert(ticketEntity);

    }

    @Override
    public RetrieveStatusResponse retrieveTicketStatus(Long id) throws ItemNotFound{
        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);

        TicketEntity entity = ticketEntity.orElseThrow(() -> new ItemNotFound("The ticket does not exist"));

        String initialStatus = entity.getStatus();
        if(entity.getStatus().equalsIgnoreCase("unchecked")) {
            entity.setStatus("checked");
            ticketRepository.save(entity);
            Ticket closedTicket = ticketEntityToTicketObjectConverter.convert(entity);
            return new RetrieveStatusResponse(
                    "The status of this ticket has been viewed and will now be closed", initialStatus);
        } else {
            return new RetrieveStatusResponse(
                    "This ticket is closed and cannot be ammended", entity.getStatus());
        }

    }
}
