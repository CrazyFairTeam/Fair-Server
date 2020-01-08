package game.controllers;

import game.core.appleTheft.Garden;
import game.core.dataBase.entities.IsStolenApplesState;
import game.core.dataBase.services.CrudGameStateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class AppleTheftController {
    @Autowired
    CrudGameStateService service;

    Garden garden;

    @RequestMapping(method = RequestMethod.GET, value = "/string")
    public String drawMap() {
        return garden.gameStatus();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/array")
    public int[][] array() {
        garden = new Garden();
        return garden.getMap();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/up")
    public int[][] up() {
        garden.up();
        return garden.getMap();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/down")
    public int[][] down() {
        garden.down();
        return garden.getMap();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/left")
    public int[][] left() {
        garden.left();
        return garden.getMap();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/right")
    public int[][] right() {
        garden.right();
        return garden.getMap();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/end")
    public boolean end() {
        return garden.isGameEnded();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/win")
    public boolean win() {

        if (garden.isGameEnded())
            service.upsert(new IsStolenApplesState(garden.isWon()));

        return garden.isWon();
    }
}
