package game.core.dataBase.entities;

import lombok.NonNull;

public class IsDrunkState extends GameState<Boolean> {
    public IsDrunkState(@NonNull Boolean value) {
        super(value);
    }

    public IsDrunkState() {
    }
}
