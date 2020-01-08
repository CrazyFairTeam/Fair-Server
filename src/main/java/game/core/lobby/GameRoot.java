package game.core.lobby;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Table(name = "gameRoot")
@Entity
public class GameRoot {
    public static final int AMOUNT_BOX_FOR_ONE_GAME = 2;
    @Column
    @Enumerated(EnumType.STRING)
    private Games game;
    @Column(length = 1000)
    private String description;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roots")
    @JsonIgnore
    private List<Day> days;

    public GameRoot(@NonNull String description, @NonNull Games game) {
        this.description = description;
        this.game = game;
    }

    public List<Day> getDays() {
        return days;
    }

    public GameRoot() {
    }

    public String getDescription() {
        return description;
    }

    public Games getGame() {
        return game;
    }

    public enum Games {
        APPLE_THEFT(0), CHOOSE_FATE(1), DRAW_RUNS(2), DRINKERS(3),
        SEQUENCE(4), END_GAME(5);

        private int numberOfGame;

        Games(int numberOfGame) {
            this.numberOfGame = numberOfGame;
        }

        public int getNumberOfGame() {
            return numberOfGame;
        }

        public String getNameOfGame() {
            return this.name();
        }
    }
}
