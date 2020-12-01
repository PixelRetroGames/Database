package ciolty.VideoDBImplementation.repositories;

import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

public class SeriesRepository extends RepositoryAbstract<SeriesData> {
    public SeriesRepository(final ResourceManager<SeriesData> resourceManager) {
        super(resourceManager);
    }
}
