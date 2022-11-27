package omogbare.sikpojie.lottery.domain.raffle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



class RaffleNumbersGeneratorTest {

//    NumberGenerator generatorMock ;

    RaffleNumbersGenerator raffleNumberGenerator = new RaffleNumbersGenerator();

    @BeforeEach
    void init() {
        raffleNumberGenerator = new RaffleNumbersGenerator();
//        generatorMock = mock(NumberGenerator.class);

    }

//    @DisplayName("Raffle Factory creates raffle numbers")
//    @Test
//    public void testFactoryCreatesRaffleNumbers()  {
//        List<Integer> numbers = Arrays.asList(3, 3, 3);
//        RaffleNumbers expected = new RaffleNumbers(numbers);
//        when(generatorMock.generate()).thenReturn(3);
//        RaffleNumbers raffleNumbers = factory.create();
//        assertEquals(expected, raffleNumbers);
//
//
//    }

    @DisplayName("Raffle Factory generates three numbers")
    @Test
    public void testFactoryCreatesThreeNumbers() {
        RaffleNumbers raffleNumbers = raffleNumberGenerator.create();
        assertEquals(3, raffleNumbers.getNumbers().size());
    }



}