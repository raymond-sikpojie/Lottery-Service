package omogbare.sikpojie.lottery.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGenerator {
    public int generate(){
        Random rand = new Random();
        int upperbound = 3;
        return rand.nextInt(upperbound);
    }
}
