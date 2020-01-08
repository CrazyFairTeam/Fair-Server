package game.core.dataBase.services;

import game.core.ObjectsWrapper;
import game.core.dataBase.entities.*;
import game.core.dataBase.repositories.BoxRepository;
import game.core.dataBase.repositories.DayRepository;
import game.core.drawRuns.Box;
import game.core.drawRuns.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PrepGameStateServ implements PreparableDB {
    @Autowired
    private CrudGameStateService crudService;
    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private BoxRepository boxRepository;

    public void prepareDB() {
        ArrayList<GameState> baseStates = initializeStates();
        updateDB(baseStates);
    }

    ArrayList<GameState> initializeStates() {
        ArrayList<GameState> baseStates = new ArrayList<>();

        baseStates.add(new SentBoxState(new Box("", new ObjectsWrapper<>(Symbol.defaultSymbol))));

        baseStates.add(new CurrentRootState(dayRepository.findById("firstDayFirstRoot").get()));
        baseStates.add(new ConsequenceState(""));
        baseStates.add(new HasMoneyState(false));
        baseStates.add(new IsStolenApplesState(false));
        baseStates.add(new IsDrunkState(true));
        baseStates.add(new GameCounterState(0));
        baseStates.add(new SentBoxCounterState(0));
        baseStates.add(new CorrectDrawSymbolsState(0));
        baseStates.add(new DayCounterState(1));

        return baseStates;
    }

    void updateDB(ArrayList<GameState> baseStates) {

        for (GameState i : baseStates)
            crudService.upsert(i);
    }
}

