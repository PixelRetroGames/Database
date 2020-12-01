package ciolty.engine.factory;

import java.util.Map;
import java.util.function.Supplier;

public class Factory<T> {
    private final Map<String, Supplier<T>> map;

    public Factory(final Map<String, Supplier<T>> map) {
        this.map = map;
    }

    /**
     * @param typeName
     * @return return object of the class mapped to typeName
     */
    public final T get(final String typeName) {
        if (!map.containsKey(typeName)) {
            return null;
        }

        Supplier<T> factory = map.get(typeName);
        return factory.get();
    }
}
