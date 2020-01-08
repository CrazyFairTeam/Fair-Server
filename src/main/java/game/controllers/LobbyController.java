package game.controllers;

import game.core.dataBase.entities.CurrentRootState;
import game.core.dataBase.repositories.DayRepository;
import game.core.dataBase.services.CrudGameStateService;
import game.core.lobby.Day;
import game.core.lobby.GameRoot;
import game.core.lobby.RootChanger;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class LobbyController {
    @Autowired
    CrudGameStateService service;
    @Autowired
    DayRepository dayRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/getGame")
    public GameRoot getGame() {
        Day day = (Day) service.get(CurrentRootState.class).get();

        int order = RootChanger.getRelevantRootNumber(day.getRoots(), service);

        RootChanger rootChanger = new RootChanger(service, dayRepository);

        return rootChanger.changeRoot(day.getRoots(), order).get(order);
    }
}
