package ciolty.MovieImplementation.entities;


import fileio.*;
import java.util.List;

public final class MovieInput implements ciolty.server.Input {
    private final List<ActorInputData> actorsData;
    private final List<UserInputData> usersData;
    private final List<ActionInputData> commandsData;
    private final List<MovieInputData> moviesData;
    private final List<SerialInputData> serialsData;

    public MovieInput() {
        this.actorsData = null;
        this.usersData = null;
        this.commandsData = null;
        this.moviesData = null;
        this.serialsData = null;
    }

    public MovieInput(Input input) {
        this.actorsData = input.getActors();
        this.usersData = input.getUsers();
        this.commandsData = input.getCommands();
        this.moviesData = input.getMovies();
        this.serialsData = input.getSerials();
    }

    public List<ActorInputData> getActors() {
        return actorsData;
    }

    public List<UserInputData> getUsers() {
        return usersData;
    }

    public List<ActionInputData> getCommands() {
        return commandsData;
    }

    public List<MovieInputData> getMovies() {
        return moviesData;
    }

    public List<SerialInputData> getSerials() {
        return serialsData;
    }
}
