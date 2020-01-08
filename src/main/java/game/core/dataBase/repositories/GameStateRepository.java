package game.core.dataBase.repositories;

import game.core.dataBase.entities.GameState;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface GameStateRepository extends CouchbaseRepository<GameState, String> {
}

