package game.core.dataBase.entities;

import lombok.NonNull;

public class GameCounterState extends GameState<Integer> {
    public GameCounterState(@NonNull Integer value) {
        super(value);
    }

    public GameCounterState() {
    }
}
