package omogbare.sikpojie.lottery.domain.raffle;

import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
public class RaffleNumbersGenerator {

    public RaffleNumbers create(){
        Random random = new Random();
        int upperbound = 3;
        List<Integer> numbers = new ArrayList<>();
        for(int i=0; i<upperbound; i++) {
           numbers.add(random.nextInt(upperbound));
        }

        return new RaffleNumbers(numbers);
    }
}
