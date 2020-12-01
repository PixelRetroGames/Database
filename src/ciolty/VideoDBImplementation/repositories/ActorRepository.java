package ciolty.VideoDBImplementation.repositories;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

public class ActorRepository extends RepositoryAbstract<ActorData> {
    public ActorRepository(final ResourceManager<ActorData> resourceManager) {
        super(resourceManager);
    }
}
