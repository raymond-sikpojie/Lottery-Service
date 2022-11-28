package omogbare.sikpojie.lottery.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
@Entity
@Getter
@Setter
@Table(name = "ticket")
public class TicketEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    @OneToMany(
            mappedBy = "ticketEntity",
            fetch = FetchType.EAGER
    )
    private List<RaffleNumberEntity> raffleEntities;

    private Instant created;

    private Instant modified;


    @PrePersist
    void preInsert(){
        created = Optional.ofNullable(created).orElseGet(Instant::now);
        modified = Optional.ofNullable(modified).orElseGet(Instant::now);
        status = Optional.ofNullable(status).orElse("unchecked");
    }

    @PreUpdate
    void preUpdate(){
        modified = Instant.now();
    }

}
