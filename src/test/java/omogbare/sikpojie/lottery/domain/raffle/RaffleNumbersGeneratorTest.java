package omogbare.sikpojie.lottery.domain.raffle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaffleNumbersGeneratorTest {


    RaffleNumbersGenerator raffleNumberGenerator = new RaffleNumbersGenerator();

    @BeforeEach
    void init() {
        raffleNumberGenerator = new RaffleNumbersGenerator();
    }

    @DisplayName("Raffle Factory generates three numbers")
    @Test
    public void testFactoryCreatesThreeNumbers() {
        RaffleNumbers raffleNumbers = raffleNumberGenerator.create();
        assertEquals(3, raffleNumbers.getNumbers().size());
    }



}