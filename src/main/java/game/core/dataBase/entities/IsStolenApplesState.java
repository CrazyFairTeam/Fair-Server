package game.core.dataBase.entities;

import lombok.NonNull;

public class IsStolenApplesState extends GameState<Boolean> {
    public IsStolenApplesState(@NonNull Boolean value) {
        super(value);
    }

    public IsStolenApplesState() {
    }
}
