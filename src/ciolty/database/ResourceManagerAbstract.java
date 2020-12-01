package ciolty.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResourceManagerAbstract<T> implements ResourceManager<T> {
    protected Map<String, T> map;

    @Override
    public final void add(final String name, final T object) {
        map.put(name, object);
    }

    @Override
    public final T get(final String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            return null;
        }
    }

    @Override
    public final List<T> getAll() {
        List<T> allObjects = new ArrayList<T>();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            allObjects.add(entry.getValue());
        }

        return allObjects;
    }
}
