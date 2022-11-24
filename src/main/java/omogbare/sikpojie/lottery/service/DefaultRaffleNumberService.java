package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.repository.RaffleNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultRaffleNumberService implements RaffleNumberService{

    @Autowired
    RaffleNumberRepository raffleNumberRepo;

    @Override
    public void saveRaffleNumber(RaffleNumberEntity raffleNumber) {
        raffleNumberRepo.save(raffleNumber);
    }
}
