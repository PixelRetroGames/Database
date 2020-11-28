package ciolty.MovieImplementation;

import ciolty.MovieImplementation.entities.MovieInput;
import ciolty.database.Repository;
import ciolty.database.ResourceManager;
import ciolty.database.ResourceManagerHashMap;
import ciolty.database.UnitOfWork;
import ciolty.MovieImplementation.entities.UserData;
import ciolty.MovieImplementation.repositories.UserRepository;
import ciolty.server.Input;
import fileio.UserInputData;

public class MoviesUnitOfWork implements UnitOfWork {
    public UserRepository userRepository;
    protected MovieInput movieInput;

    @Override
    public void populate(Input input) {
        movieInput = (MovieInput)input;

        ResourceManager<UserData> resourceManager = new ResourceManagerHashMap<UserData>();
        for (UserInputData userInputData : movieInput.getUsers()) {
            resourceManager.add(userInputData.getUsername(), new UserData(userInputData));
        }
        userRepository = new UserRepository(resourceManager);
    }

    @Override
    public void terminate() { }
}
