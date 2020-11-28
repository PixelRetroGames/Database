package ciolty.VideoDBImplementation.repositories;

import ciolty.database.RepositoryAbstract;
import ciolty.database.ResourceManager;
import ciolty.VideoDBImplementation.entities.UserData;

public class UserRepository extends RepositoryAbstract<UserData> {
    public UserRepository(final ResourceManager<UserData> resourceManager) {
        super(resourceManager);
    }
}
