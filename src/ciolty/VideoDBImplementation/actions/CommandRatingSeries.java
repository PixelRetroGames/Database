package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.SeasonData;
import ciolty.VideoDBImplementation.entities.SeriesData;

import java.util.logging.Level;

public final class CommandRatingSeries extends VideoDBAction {
    private String getSeriesValidity(final SeriesData seriesData) {
        if (seriesData == null) {
            return "Error: series " + seriesData.getTitle() + " not found";
        } else {
            return null;
        }
    }

    private String getSeasonValidity(final SeasonData seasonData) {
        if (seasonData == null) {
            return "Error: season " + seasonData.getCurrentSeason() + " not found";
        } else {
            return null;
        }
    }

    @Override
    public String execute() {
        LOGGER.log(Level.INFO, "Rated series " + actionData.getTitle()
                + " season " + actionData.getSeasonNumber());
        SeriesData seriesData = getUnitOfWork().getSeriesRepository().get(actionData.getTitle());
        String message = getSeriesValidity(seriesData);

        if (seriesData == null) {
            return message;
        }

        if (actionData.getSeasonNumber() >= seriesData.getNumberOfSeasons()) {
            message = "error: season " + actionData.getSeasonNumber()
                    + " does not exist in " + actionData.getTitle() + "!";
            return message;
        }

        SeasonData seasonData = seriesData.getSeasons().get(actionData.getSeasonNumber());

        message = getSeasonValidity(seasonData);

        if (seasonData == null) {
            return message;
        }

        double givenRating = actionData.getGrade();
        double rating = seasonData.getRating();
        int numberOfRatings = seasonData.getNumberOfRatings();

        if (numberOfRatings == 0) {
            rating = givenRating;
        } else {
            rating = (rating * numberOfRatings + givenRating) / (numberOfRatings + 1);
        }

        numberOfRatings++;

        seasonData.setNumberOfRatings(numberOfRatings);
        seasonData.setRating(rating);

        double seriesRating = 0.0f;
        for (SeasonData seasonDataIterator : seriesData.getSeasons()) {
            seriesRating += seasonDataIterator.getRating();
        }
        seriesRating /= seriesData.getNumberOfSeasons();

        message = "success -> " + seriesData.getTitle() + " was rated with "
                + rating + " by " + actionData.getUsername();
        return message;
    }
}
