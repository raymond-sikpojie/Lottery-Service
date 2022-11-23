package omogbare.sikpojie.lottery.service;

import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import omogbare.sikpojie.lottery.repository.RaffleNumberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultRaffleNumberService implements RaffleNumberService{

    @Autowired
    RaffleNumberEntityRepository repo;

    @Override
    public void saveRaffleNumber(RaffleNumberEntity raffleNumber) {
        repo.save(raffleNumber);
    }
}
