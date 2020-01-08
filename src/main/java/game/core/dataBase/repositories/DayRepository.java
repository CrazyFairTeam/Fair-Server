package game.core.dataBase.repositories;

import game.core.lobby.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, String> {
}

