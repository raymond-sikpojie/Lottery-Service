package omogbare.sikpojie.lottery.lottery;

import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.value.Outcome;
import org.springframework.stereotype.Component;


@Component
public class OutcomeGenerator {

    public Outcome create(RaffleNumbers raffleNumbers) {
        long distinctNumbers = raffleNumbers.getNumbers().stream().distinct().count();
        int firstNum = raffleNumbers.getNumbers().get(0);
        int secondNum = raffleNumbers.getNumbers().get(1);
        int thirdNum = raffleNumbers.getNumbers().get(2);

        if(distinctNumbers == 1){
            return  new Outcome(5);
        }
        else if(secondNum != firstNum && thirdNum != firstNum) {
            return new Outcome(1);
        }

        else if(firstNum + secondNum + thirdNum == 2) {
            return new Outcome(10);
        }

        return new Outcome(0);
    }

}
