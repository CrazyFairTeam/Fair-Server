package game.controllers;

import game.core.chooseFate.History;
import game.core.chooseFate.HitApple;
import game.core.chooseFate.Litigation;
import game.core.dataBase.entities.ConsequenceState;
import game.core.dataBase.entities.DayCounterState;
import game.core.dataBase.entities.HasMoneyState;
import game.core.dataBase.entities.IsStolenApplesState;
import game.core.dataBase.repositories.HistoryRepository;
import game.core.dataBase.services.CrudGameStateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class ChooseFateController {
    @Autowired
    CrudGameStateService service;
    @Autowired
    HistoryRepository historyRepository;

    private History history;

    @RequestMapping(method = RequestMethod.GET, value = "/getLigation")
    public Litigation getLigation() {
        int dayNumber = (Integer) service.get(DayCounterState.class).get();
        history = historyRepository.findById(dayNumber).get();

        return history.getLitigation();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/forgivePrisoner")
    public void forgivePrisoner() {
        service.upsert(new ConsequenceState(history.getNegSequence()));

        if (isPrisonerDonated())
            service.upsert(new HasMoneyState(true));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hasApple")
    public boolean hasApple() {
        boolean hasApple = (boolean) service.get(IsStolenApplesState.class).get();

        return hasApple;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkHit")
    public void checkHit(double x, double y) {

        if (HitApple.isHited(x, y)) {
            service.upsert(new ConsequenceState(history.getPosSequence()));
        } else {
            service.upsert(new ConsequenceState(history.getNegSequence()));
        }
    }

    private boolean isPrisonerDonated() {
        return new Random().nextBoolean();
    }
}