package ciolty.VideoDBImplementation.entities;

import fileio.MovieInputData;
import fileio.ShowInput;

public final class MovieData extends ShowData {
    private final int duration;
    private double rating;
    private int numberOfRatings;

    public MovieData(final MovieInputData inputData) {
        super((ShowInput) (inputData));
        this.duration = inputData.getDuration();
        rating = 0.0f;
        numberOfRatings = 0;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
