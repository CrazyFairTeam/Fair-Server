package game.core.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import game.core.ObjectsWrapper;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Table(name = "day")
@Entity
public class Day {
    @JsonProperty("id")
    @Id
    @Column(length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Days_GameRoots",
            joinColumns = @JoinColumn(name = "day_id"),
            inverseJoinColumns = @JoinColumn(name = "gameRoot_id")
    )
    private List<GameRoot> roots;

    public Day(@NonNull String description, @NonNull ObjectsWrapper<GameRoot> roots) {
        this.description = description;
        this.roots = new ArrayList<>();
        Collections.addAll(this.roots, roots.getObjects());
    }

    public List<GameRoot> getRoots() {
        return roots;
    }

    public Day() {
    }

    public String getDescription() {
        return description;
    }
}
