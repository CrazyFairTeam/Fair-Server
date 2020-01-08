package game.core.drawRuns;

import game.core.ObjectsWrapper;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Table(name = "box")
@Entity
public class Box {
    @Id
    @Column(name = "id")
    private String nameGoods;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Boxes_Symbols",
            joinColumns = @JoinColumn(name = "box_id"),
            inverseJoinColumns = @JoinColumn(name = "symbol_id")
    )
    @OrderColumn
    private List<Symbol> symbols;

    public Box(@NonNull String nameGoods, @NonNull ObjectsWrapper<Symbol> symbols) {
        ArrayList<Symbol> symbolsList = new ArrayList<>();
        Collections.addAll(symbolsList, symbols.getObjects());

        if (isCorrectSymbolsEmpty(symbolsList))
            throw new IllegalArgumentException("correct Symbols are Empty");

        this.nameGoods = nameGoods;
        this.symbols = symbolsList;
    }

    public Box() {
    }

    public String getNameGoods() {
        return nameGoods;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    private boolean isCorrectSymbolsEmpty(List<Symbol> correctSymbols) {
        for (Symbol i : correctSymbols) {

            if (i != null)
                return false;
        }

        return true;
    }
}
