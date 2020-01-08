package game.core.dataBase.entities;

import game.core.drawRuns.Box;
import lombok.NonNull;

public class SentBoxState extends GameState<Box> {
    public SentBoxState(@NonNull Box value) {
        super(value);
    }

    public SentBoxState() {
    }
}
