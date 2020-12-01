package ciolty.VideoDBImplementation.repositories;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

public class MovieRepository extends RepositoryAbstract<MovieData> {
    public MovieRepository(final ResourceManager<MovieData> resourceManager) {
        super(resourceManager);
    }
}
