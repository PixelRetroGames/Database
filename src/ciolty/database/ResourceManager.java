package ciolty.database;

import java.util.List;

/**
 * Data mapper of objects of type T
 * @param <T>
 */
public interface ResourceManager<T> {
    /**
     * Add object to data mapper
     * @param name
     * @param object
     */
    void add(String name, T object);

    /**
     * @param name
     * @return object with the given name from the data mapper
     */
    T get(String name);

    /**
     * @return List of all the objects stored in data mapper
     */
    List<T> getAll();
}
