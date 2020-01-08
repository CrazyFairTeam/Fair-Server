package game.core.dataBase.entities;

import game.core.lobby.Day;
import lombok.NonNull;

public class CurrentRootState extends GameState<Day> {
    public CurrentRootState(@NonNull Day value) {
        super(value);
    }

    public CurrentRootState() {
    }
}
