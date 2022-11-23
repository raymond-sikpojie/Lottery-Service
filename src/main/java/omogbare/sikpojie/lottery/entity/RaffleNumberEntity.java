package omogbare.sikpojie.lottery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name="raffle_number")
public class RaffleNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ticket_id", nullable=false)
    private TicketEntity ticketEntity;


    private Instant created;
    private Instant modified;

    @PrePersist
    void preInsert(){
        created = Optional.ofNullable(created).orElseGet(Instant::now);
        modified = Optional.ofNullable(modified).orElseGet(Instant::now);
    }

    @PreUpdate
    void preUpdate(){
        modified = Instant.now();
    }

}
