package game.core.dataBase.entities;

import lombok.NonNull;

public class HasMoneyState extends GameState<Boolean> {
    public HasMoneyState(@NonNull Boolean value) {
        super(value);
    }

    public HasMoneyState() {
    }
}
