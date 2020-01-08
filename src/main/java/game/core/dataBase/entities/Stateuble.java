package game.core.dataBase.entities;

import org.springframework.data.couchbase.core.mapping.Document;

@Document
public interface Stateuble<T> {
    public T get();
}
