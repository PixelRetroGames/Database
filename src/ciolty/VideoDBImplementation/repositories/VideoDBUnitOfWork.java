package ciolty.VideoDBImplementation.repositories;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.VideoDBImplementation.entities.VideoDBInput;
import ciolty.VideoDBImplementation.resourceManagers.ResourceManagerLinkedHashMap;
import ciolty.engine.database.ResourceManager;
import ciolty.VideoDBImplementation.resourceManagers.ResourceManagerHashMap;
import ciolty.engine.database.UnitOfWork;
import ciolty.engine.server.Input;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

public final class VideoDBUnitOfWork implements UnitOfWork {
    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private SeriesRepository seriesRepository;
    private ActorRepository actorRepository;

    private void populateUserRepository(final VideoDBInput videoDBInput) {
        ResourceManager<UserData> resourceManager = new ResourceManagerHashMap<UserData>();
        for (UserInputData userInputData : videoDBInput.getUsers()) {
            resourceManager.add(userInputData.getUsername(), new UserData(userInputData));
        }
        userRepository = new UserRepository(resourceManager);
    }

    private void populateMovieRepository(final VideoDBInput videoDBInput) {
        ResourceManager<MovieData> resourceManager = new ResourceManagerLinkedHashMap<>();
        for (MovieInputData movieInputData : videoDBInput.getMovies()) {
            resourceManager.add(movieInputData.getTitle(), new MovieData(movieInputData));
        }
        movieRepository = new MovieRepository(resourceManager);
    }

    private void populateSeriesRepository(final VideoDBInput videoDBInput) {
        ResourceManager<SeriesData> resourceManager = new ResourceManagerLinkedHashMap<>();
        for (SerialInputData seriesInputData : videoDBInput.getSerials()) {
            resourceManager.add(seriesInputData.getTitle(), new SeriesData(seriesInputData));
        }
        seriesRepository = new SeriesRepository(resourceManager);
    }

    private void populateActorRepository(final VideoDBInput videoDBInput) {
        ResourceManager<ActorData> resourceManager = new ResourceManagerLinkedHashMap<>();
        for (ActorInputData actorInputData : videoDBInput.getActors()) {
            resourceManager.add(actorInputData.getName(), new ActorData(actorInputData));
        }
        actorRepository = new ActorRepository(resourceManager);
    }

    @Override
    public void populate(final Input input) {
        VideoDBInput videoDBInput = (VideoDBInput) input;

        populateUserRepository(videoDBInput);
        populateMovieRepository(videoDBInput);
        populateSeriesRepository(videoDBInput);
        populateActorRepository(videoDBInput);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public SeriesRepository getSeriesRepository() {
        return seriesRepository;
    }

    public ActorRepository getActorRepository() {
        return actorRepository;
    }

    @Override
    public void terminate() { }
}
