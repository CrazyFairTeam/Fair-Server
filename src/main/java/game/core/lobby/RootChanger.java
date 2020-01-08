package game.core.lobby;

import game.core.dataBase.entities.*;
import game.core.dataBase.repositories.DayRepository;
import game.core.dataBase.services.CrudGameStateService;
import lombok.NonNull;

import java.util.List;

public class RootChanger {

    private CrudGameStateService crudService;
    private DayRepository dayRepository;

    public RootChanger(@NonNull CrudGameStateService crudService, @NonNull DayRepository dayRepository) {
        this.crudService = crudService;
        this.dayRepository = dayRepository;
    }

    public RootChanger() {
    }

    public static int getRelevantRootNumber(List<GameRoot> root, CrudGameStateService crudService) {
        int order = (int) crudService.get(GameCounterState.class).get();
        crudService.upsert(new GameCounterState(order + 1));

        if (order == root.size()) {

            int day = (int) crudService.get(DayCounterState.class).get();
            crudService.upsert(new DayCounterState(day + 1));

            crudService.upsert(new IsStolenApplesState(false));
            crudService.upsert(new HasMoneyState(false));
            order = 0;

            crudService.upsert(new GameCounterState(order + 1));
        }

        return order;
    }

    public List<GameRoot> changeRoot(List<GameRoot> root, int order) {

        if ((int) crudService.get(DayCounterState.class).get() == 1) {

            if (root.get(order).getGame() == GameRoot.Games.DRAW_RUNS)
                root = getRelRootAtDrawRuns(root);

            else if (root.get(order).getGame() == GameRoot.Games.DRINKERS)
                root = getRelRootAtDrinkers(root);

        } else if ((int) crudService.get(DayCounterState.class).get() == 2) {

            if (root.get(order).getGame() == GameRoot.Games.APPLE_THEFT)
                root = getRelRootAtSecDayFirGame(root);


            else if (root.get(order).getGame() == GameRoot.Games.DRAW_RUNS)
                root = getRelRootAtSecDayDrinkers(root);

            else if (root.get(order).getGame() == GameRoot.Games.DRINKERS)
                root = getRelRootAtDayConsequence(root);

        } else if ((Integer) crudService.get(DayCounterState.class).get() == 3) {
            root = getRelRootAtThirdDay(root);
        }

        return root;
    }

    private List<GameRoot> getRelRootAtSecDayFirGame(List<GameRoot> root) {

        if ((Boolean) crudService.get(IsDrunkState.class).get()) {
            List<GameRoot> newGameRoot = dayRepository.findById("secondDaySecondRoot").get().getRoots();
            root = newGameRoot;
        } else {
            List<GameRoot> newGameRoot = dayRepository.findById("secondDayFirstRoot").get().getRoots();
            root = newGameRoot;
        }

        return root;
    }

    private List<GameRoot> getRelRootAtDrawRuns(List<GameRoot> root) {

        if ((boolean) crudService.get(HasMoneyState.class).get()) {
            List<GameRoot> newGameRoot = dayRepository.findById("firstDaySecondRoot").get().getRoots();
            root = newGameRoot;
        }

        return root;
    }

    private List<GameRoot> getRelRootAtDrinkers(List<GameRoot> root) {

        if (!(boolean) crudService.get(HasMoneyState.class).get()) {
            List<GameRoot> newGameRoot = dayRepository.findById("firstDayThirdRoot").get().getRoots();
            crudService.upsert(new IsDrunkState(false));
            root = newGameRoot;
        }
        return root;
    }

    private List<GameRoot> getRelRootAtSecDayDrinkers(List<GameRoot> root) {

        if ((boolean) crudService.get(HasMoneyState.class).get()) {
            List<GameRoot> newGameRoot = dayRepository.findById("secondDaySixthRoot").get().getRoots();
            root = newGameRoot;
        }
        return root;
    }

    private List<GameRoot> getRelRootAtDayConsequence(List<GameRoot> root) {
        if (!(boolean) crudService.get(HasMoneyState.class).get()) {
            List<GameRoot> newGameRoot = dayRepository.findById("secondDayFifthRoot").get().getRoots();
            crudService.upsert(new IsDrunkState(false));
            root = newGameRoot;
        }
        return root;
    }

    private List<GameRoot> getRelRootAtThirdDay(List<GameRoot> root) {
        root = dayRepository.findById("thirdDay").get().getRoots();
        return root;
    }
}
