package ciolty.VideoDBImplementation.resourceManagers;

import ciolty.database.ResourceManagerAbstract;

import java.util.HashMap;

public class ResourceManagerHashMap<T> extends ResourceManagerAbstract<T> {
    public ResourceManagerHashMap() {
        map = new HashMap<>();
    }
}
