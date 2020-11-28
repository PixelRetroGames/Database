package ciolty.database;

import java.util.ArrayList;
import java.util.List;

public interface Repository<T> {
    public T get(String name);
    public List<T> find(Filter filter);
}
