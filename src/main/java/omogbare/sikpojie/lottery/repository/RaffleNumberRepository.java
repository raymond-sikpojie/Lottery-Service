package omogbare.sikpojie.lottery.repository;

import omogbare.sikpojie.lottery.entity.RaffleNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleNumberRepository extends JpaRepository<RaffleNumberEntity, Long> {
}
