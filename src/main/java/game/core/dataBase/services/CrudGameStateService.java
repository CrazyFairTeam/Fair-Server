package game.core.dataBase.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.core.dataBase.entities.GameState;
import game.core.dataBase.repositories.GameStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrudGameStateService {
    @Autowired
    private GameStateRepository stateRepository;

    public CrudGameStateService() {
    }

    //Сrutch
    public void upsert(GameState state) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String stringState = objectMapper.writeValueAsString(state);
            GameState gameState = objectMapper.readValue(stringState, GameState.class);
            stateRepository.save(gameState);
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public GameState get(Class id) {
        return stateRepository.findById(id.getSimpleName()).get();
    }

}
