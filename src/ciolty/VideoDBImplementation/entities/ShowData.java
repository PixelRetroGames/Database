package ciolty.VideoDBImplementation.entities;

import fileio.ShowInput;

import java.util.ArrayList;

public class ShowData {
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;

    public ShowData(final ShowInput inputData) {
        this.title = inputData.getTitle();
        this.year = inputData.getYear();
        this.cast = inputData.getCast();
        this.genres = inputData.getGenres();
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }
}
