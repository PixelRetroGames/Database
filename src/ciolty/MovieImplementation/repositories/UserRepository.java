package ciolty.MovieImplementation.repositories;

import ciolty.database.RepositoryAbstract;
import ciolty.database.ResourceManager;
import ciolty.MovieImplementation.entities.UserData;

public class UserRepository extends RepositoryAbstract<UserData> {
    public UserRepository(ResourceManager<UserData> resourceManager) {
        super(resourceManager);
    }
}
