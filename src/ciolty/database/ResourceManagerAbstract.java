package ciolty.database;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResourceManagerAbstract<T> implements ResourceManager<T> {
    protected Map<String, T> map;

    @Override
    public void add(String name, T object) {
        map.put(name, object);
    }

    @Override
    public void add(List<String> names, List<T> objects) {
        if (names.size() != objects.size()) {
            System.err.println("Names and objects don't have the same size!");
            throw new InvalidParameterException();
        }
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i), objects.get(i));
        }
    }

    @Override
    public T get(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            System.err.println("Key not found in map!");
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        List<T> allObjects = new ArrayList<T>();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            allObjects.add(entry.getValue());
        }

        return allObjects;
    }
}
