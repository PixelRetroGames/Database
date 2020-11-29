package ciolty.VideoDBImplementation.resourceManagers;

import ciolty.database.ResourceManagerAbstract;

import java.util.LinkedHashMap;

public class ResourceManagerLinkedHashMap<T> extends ResourceManagerAbstract<T> {
    public ResourceManagerLinkedHashMap() {
        map = new LinkedHashMap<>();
    }
}
