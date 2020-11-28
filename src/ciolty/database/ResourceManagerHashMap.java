package ciolty.database;

import java.util.HashMap;

public class ResourceManagerHashMap<T> extends ResourceManagerAbstract<T> {
    public ResourceManagerHashMap() {
        map = new HashMap<String, T>();
    }
}
