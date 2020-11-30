package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.SeasonData;
import ciolty.VideoDBImplementation.entities.SeriesData;

import java.util.logging.Level;

public final class CommandRatingSeries extends VideoDBAction {
    private SeriesData seriesData;
    private SeasonData seasonData;

    @Override
    public String start() {
        seriesData = getUnitOfWork().getSeriesRepository().get(actionData.getTitle());
        if (seriesData == null) {
            return "Error: series " + seriesData.getTitle() + " not found";
        }

        if (actionData.getSeasonNumber() >= seriesData.getNumberOfSeasons()) {
            return "error: season " + actionData.getSeasonNumber()
                    + " does not exist in " + actionData.getTitle() + "!";
        }

        seasonData = seriesData.getSeasons().get(actionData.getSeasonNumber());
        if (seasonData == null) {
            return "Error: season " + seasonData.getCurrentSeason() + " not found";
        }

        return null;
    }

    @Override
    public String execute() {
        LOGGER.log(Level.INFO, "Rated series " + actionData.getTitle()
                + " season " + actionData.getSeasonNumber());
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

        return "success -> " + seriesData.getTitle() + " was rated with "
                + rating + " by " + actionData.getUsername();
    }
}
