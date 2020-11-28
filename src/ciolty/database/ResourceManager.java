package ciolty.database;

import java.util.List;

public interface ResourceManager<T> {
    public void add(String name, T object);
    public void add(List<String> names, List<T> objects);
    public T get(String name);
    public List<T> getAll();
}
