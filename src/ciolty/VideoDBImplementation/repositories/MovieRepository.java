package ciolty.VideoDBImplementation.repositories;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.database.RepositoryAbstract;
import ciolty.database.ResourceManager;

public class MovieRepository extends RepositoryAbstract<MovieData> {
    public MovieRepository(final ResourceManager<MovieData> resourceManager) {
        super(resourceManager);
    }
}
