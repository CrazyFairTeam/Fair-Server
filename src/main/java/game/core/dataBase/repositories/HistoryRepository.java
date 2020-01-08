package game.core.dataBase.repositories;

import game.core.chooseFate.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}

