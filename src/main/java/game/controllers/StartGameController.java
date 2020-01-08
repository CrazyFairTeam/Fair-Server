package game.controllers;

import game.core.dataBase.services.PrepGameStateServ;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class StartGameController {
    @Autowired
    PrepGameStateServ prepGameStateServ;

    @RequestMapping(method = RequestMethod.GET, value = "/startNewGame")
    public void startNewGame() {
        prepGameStateServ.prepareDB();
    }
}
