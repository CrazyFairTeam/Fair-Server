package game.controllers;

import game.core.dataBase.entities.CorrectDrawSymbolsState;
import game.core.dataBase.entities.HasMoneyState;
import game.core.dataBase.entities.SentBoxCounterState;
import game.core.dataBase.entities.SentBoxState;
import game.core.dataBase.repositories.BoxRepository;
import game.core.dataBase.services.CrudGameStateService;
import game.core.drawRuns.Box;
import game.core.drawRuns.Symbol;
import game.core.lobby.GameRoot;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameCommands")
@Api(value = "gameCommands", description = "list of mini-game commands")
public class DrawRunsController {
    @Autowired
    CrudGameStateService service;
    @Autowired
    BoxRepository boxRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/getNameGoods")
    public String getNameGoods() {
        Box box = boxRepository.getRandomBox();
        int amountSentBox = (Integer) service.get(SentBoxCounterState.class).get();

        service.upsert(new SentBoxCounterState(amountSentBox + 1));
        service.upsert(new SentBoxState(box));

        return box.getNameGoods();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkSymbol")
    public void checkSymbol(String[] appearance) {
        Box box = (Box) service.get(SentBoxState.class).get();

        Symbol symbol = new Symbol(Symbol.transformToAppearance(appearance));

        if (Symbol.isSymbolCorrect(symbol, box.getSymbols())) {

            int correctDrawSymbols = (int) service.get(CorrectDrawSymbolsState.class).get();
            service.upsert(new CorrectDrawSymbolsState(correctDrawSymbols + 1));
        }

        if ((Integer) service.get(SentBoxCounterState.class).get() == GameRoot.AMOUNT_BOX_FOR_ONE_GAME) {

            if ((Integer) service.get(CorrectDrawSymbolsState.class).get() == GameRoot.AMOUNT_BOX_FOR_ONE_GAME)
                service.upsert(new HasMoneyState(true));

            service.upsert(new SentBoxCounterState(0));
            service.upsert(new CorrectDrawSymbolsState(0));
        }
    }
}