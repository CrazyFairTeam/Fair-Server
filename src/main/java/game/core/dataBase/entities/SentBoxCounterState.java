package game.core.dataBase.entities;

import lombok.NonNull;

public class SentBoxCounterState extends GameState<Integer> {
    public SentBoxCounterState(@NonNull Integer value) {
        super(value);
    }

    public SentBoxCounterState() {
    }
}
