package game.core.dataBase.entities;

import lombok.NonNull;

public class ConsequenceState extends GameState<String> {
    public ConsequenceState(@NonNull String value) {
        super(value);
    }

    public ConsequenceState() {
    }
}
