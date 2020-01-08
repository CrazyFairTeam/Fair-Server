package game.controllers;

import game.core.dataBase.entities.ConsequenceState;
import game.core.dataBase.entities.IsDrunkState;
import game.core.dataBase.services.CrudGameStateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class ConsequenceChoiceController {
    @Autowired
    CrudGameStateService service;

    @RequestMapping(method = RequestMethod.GET, value = "/getConsequence")
    public String getConsequence() {
        return (String) service.get(ConsequenceState.class).get();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/isDrunk")
    public boolean isDrunk() {
        return (boolean) service.get(IsDrunkState.class).get();
    }
}


