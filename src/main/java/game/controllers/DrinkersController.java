package game.controllers;

import game.core.dataBase.IsDrunkState;
import game.core.drinkers.Game;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class DrinkersController extends Controller {
    static public Game game = new Game();

    @RequestMapping(method = RequestMethod.GET, value = "/drink")
    public int[] drink() {
        game.getPlayer().drink();
        game.getNpc().npcDrink();
        int[] stats = {game.getPlayer().getAlcohol(), game.getNpc().getAlcohol()};
        return stats;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pass")
    public int[] pass() {
        game.getPlayer().pass();
        while (game.getNpc().getAlcohol() < 850) {
            game.getNpc().npcDrink();
        }
        int[] stats = {game.getPlayer().getAlcohol(), game.getNpc().getAlcohol()};
        return stats;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/winner")
    public String whoHasWon() {
        String s = '\"' + game.whoIsWinner() + '\"';
        return s;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/prediction")
    public boolean isPredictioner() {
        boolean isDrunk = game.getPlayer().isAbleToSeePrediction();
        mapBase.replace(IsDrunkState.class, new IsDrunkState(!isDrunk));
        return isDrunk;
    }

}
