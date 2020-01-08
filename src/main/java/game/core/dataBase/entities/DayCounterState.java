package game.core.dataBase.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NonNull;

@JsonDeserialize
public class DayCounterState extends GameState<Integer> {
    public DayCounterState(@NonNull Integer value) {
        super(value);
    }

    public DayCounterState() {
    }
}
