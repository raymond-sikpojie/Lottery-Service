package omogbare.sikpojie.lottery.entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Basic
    private Boolean drawn;

    private Instant created;

    private Instant modified;


    @PrePersist
    void preInsert(){
        created = Optional.ofNullable(created).orElseGet(Instant::now);
        modified = Optional.ofNullable(modified).orElseGet(Instant::now);
        drawn = Optional.ofNullable(drawn).orElse(false);
    }

    @PreUpdate
    void preUpdate(){
        modified = Instant.now();
    }

}
