package omogbare.sikpojie.lottery.lottery;

import omogbare.sikpojie.lottery.domain.raffle.RaffleNumbers;
import omogbare.sikpojie.lottery.value.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OutcomeGeneratorTest {
    OutcomeGenerator outcomeGenerator = new OutcomeGenerator();


    @DisplayName("Sum of raffle numbers returns 2 has outcome 10")
    @Test
    public void testRuleForSumTotalIsTwo() {
        List<Integer> numbers =  Arrays.asList(1,1,0);
        RaffleNumbers raffleNumbers = new RaffleNumbers(numbers);
        Outcome outcome = outcomeGenerator.create(raffleNumbers);
        assertEquals(new Outcome(10), outcome);
    }

    @DisplayName("All Raffle Numbers same return 5")
    @Test
    public void testRuleForAllNumbersSameInRaffle(){
        List<Integer> numbers =  Arrays.asList(1,1,1);

        RaffleNumbers raffleNumbers = new RaffleNumbers(numbers);
        Outcome outcome = outcomeGenerator.create(raffleNumbers);
        assertEquals(new Outcome(5), outcome);

    }

    @DisplayName("If the last two numbers are different from the first")
    @ParameterizedTest
    @MethodSource
    public void testRuleIfSecondAndThirdNumbersDifferFromTheFirst (RaffleNumbers raffleNumbers, Outcome outcome) {
        Outcome result = outcomeGenerator.create(raffleNumbers);
        assertEquals(outcome,result);
    }


    private static Stream<Arguments> testRuleIfSecondAndThirdNumbersDifferFromTheFirst() {
        return Stream.of(
                Arguments.of(
                        new RaffleNumbers(Arrays.asList(1,2,2)),
                        new Outcome(1)
                ),
                Arguments.of(
                        new RaffleNumbers(Arrays.asList(1,3,4)),
                        new Outcome(1)
                ),
                Arguments.of(
                        new RaffleNumbers(Arrays.asList(1,1,0)),
                        new Outcome(10)
                ),
                Arguments.of(
                        new RaffleNumbers(Arrays.asList(1,3,1)),
                        new Outcome(0)
                ),
                Arguments.of(
                        new RaffleNumbers(Arrays.asList(1,1,1)),
                        new Outcome(5)
                )

        );
    }





}