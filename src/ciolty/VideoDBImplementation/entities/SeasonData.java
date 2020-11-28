package ciolty.VideoDBImplementation.entities;

import entertainment.Season;

public final class SeasonData {
    private final int currentSeason;
    private int duration;
    private double rating;
    private int numberOfRatings;

    public SeasonData(final Season inputData) {
        this.currentSeason = inputData.getCurrentSeason();
        this.duration = inputData.getDuration();
        this.rating = 0;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }
}
