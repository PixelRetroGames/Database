package ciolty.MovieImplementation.entities;

import ciolty.action.Output;

/**
 * View controller: takes data and transforms it in Output
 */
public class MovieOutput implements Output {
    private final int id;
    private final String message;

    public MovieOutput(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
