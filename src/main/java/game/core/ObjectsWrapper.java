package game.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class ObjectsWrapper<T> {
    private T[] objects;

    public ObjectsWrapper(T... objects) {
        this.objects = objects;
    }

    public ObjectsWrapper() {
    }

    public T[] getObjects() {
        return objects;
    }
}
