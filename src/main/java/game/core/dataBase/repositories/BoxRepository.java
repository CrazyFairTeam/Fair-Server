package game.core.dataBase.repositories;

import game.core.drawRuns.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoxRepository extends JpaRepository<Box, String> {
    @Query(value = "SELECT * FROM box ORDER BY random() LIMIT 1", nativeQuery = true)
    Box getRandomBox();
}

