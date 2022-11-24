package omogbare.sikpojie.lottery.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name="raffle_number")
public class RaffleNumberEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ticketId", nullable=false)
    private TicketEntity ticketEntity;

    @Convert(converter = RaffleNumbersConverter.class)
    private List<Integer> numbers;
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



@Converter
class RaffleNumbersConverter implements AttributeConverter<List,String>{
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List attribute) {
        String raffleNumbersJson = null;
        try {
            raffleNumbersJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
        return raffleNumbersJson;
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
        List<Integer> raffleNumber = null;

        try {
            raffleNumber = objectMapper.readValue(dbData, new TypeReference<>() {});
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
        return raffleNumber;
    }
}