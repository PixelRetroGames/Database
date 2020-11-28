package ciolty.factory;

import java.util.Map;
import java.util.function.Supplier;

public class Factory<T> {
    private final Map<String, Supplier<T>> map;

    public Factory(Map<String, Supplier<T>> map) {
        this.map = map;
    }

    public T get(String typeName) {
        if (!map.containsKey(typeName)) {
            return null;
        }

        Supplier<T> factory = map.get(typeName);
        return factory.get();
    }
}
