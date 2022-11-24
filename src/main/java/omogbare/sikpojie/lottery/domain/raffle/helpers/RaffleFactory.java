package omogbare.sikpojie.lottery.domain.raffle.helpers;

import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
public class RaffleFactory {

    public RaffleNumbers create(){
        Random random = new Random();
        int upperbound = 3;

        List<Integer> numbers = new ArrayList<>();
        for(int i=0; i<3; i++) {
           numbers.add(random.nextInt(upperbound));
        }


        return new RaffleNumbers(numbers);
    }
}
