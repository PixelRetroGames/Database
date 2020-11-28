package ciolty.VideoDBImplementation.repositories;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.entities.VideoDBInput;
import ciolty.database.ResourceManager;
import ciolty.database.ResourceManagerHashMap;
import ciolty.database.UnitOfWork;
import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.server.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

public final class VideoDBUnitOfWork implements UnitOfWork {
    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private SeriesRepository seriesRepository;

    private void populateUserRepository(final VideoDBInput videoDBInput) {
        ResourceManager<UserData> resourceManager = new ResourceManagerHashMap<UserData>();
        for (UserInputData userInputData : videoDBInput.getUsers()) {
            resourceManager.add(userInputData.getUsername(), new UserData(userInputData));
        }
        userRepository = new UserRepository(resourceManager);
    }

    private void populateMovieRepository(final VideoDBInput videoDBInput) {
        ResourceManager<MovieData> resourceManager = new ResourceManagerHashMap<MovieData>();
        for (MovieInputData movieInputData : videoDBInput.getMovies()) {
            resourceManager.add(movieInputData.getTitle(), new MovieData(movieInputData));
        }
        movieRepository = new MovieRepository(resourceManager);
    }

    private void populateSeriesRepository(final VideoDBInput videoDBInput) {
        ResourceManager<SeriesData> resourceManager = new ResourceManagerHashMap<SeriesData>();
        for (SerialInputData seriesInputData : videoDBInput.getSerials()) {
            resourceManager.add(seriesInputData.getTitle(), new SeriesData(seriesInputData));
        }
        seriesRepository = new SeriesRepository(resourceManager);
    }

    @Override
    public void populate(final Input input) {
        VideoDBInput videoDBInput = (VideoDBInput) input;

        populateUserRepository(videoDBInput);
        populateMovieRepository(videoDBInput);
        populateSeriesRepository(videoDBInput);
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

    @Override
    public void terminate() { }
}
