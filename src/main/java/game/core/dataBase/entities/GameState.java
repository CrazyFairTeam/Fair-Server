package game.core.dataBase.entities;

import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConsequenceState.class),
        @JsonSubTypes.Type(value = CorrectDrawSymbolsState.class),
        @JsonSubTypes.Type(value = CurrentRootState.class),
        @JsonSubTypes.Type(value = DayCounterState.class),
        @JsonSubTypes.Type(value = GameCounterState.class),
        @JsonSubTypes.Type(value = HasMoneyState.class),
        @JsonSubTypes.Type(value = IsDrunkState.class),
        @JsonSubTypes.Type(value = IsStolenApplesState.class),
        @JsonSubTypes.Type(value = SentBoxCounterState.class),
        @JsonSubTypes.Type(value = SentBoxState.class),
})
@JsonDeserialize
@Document
public abstract class GameState<T> implements Stateuble {

    @JsonProperty
    private T val;
    @Id
    @JsonIgnore
    private String id;

    public String getId() {
        return id;
    }

    @Override
    public T get() {
        return val;
    }

    @JsonCreator
    public GameState(T value) {
        this.val = value;
        id = this.getClass().getSimpleName();
    }

    public GameState() {
        id = this.getClass().getSimpleName();
    }
}
