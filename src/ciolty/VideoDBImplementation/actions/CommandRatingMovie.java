package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;

import java.util.logging.Level;

public final class CommandRatingMovie extends VideoDBAction {
    private MovieData movieData;

    @Override
    public String start() {
        movieData = getUnitOfWork().getMovieRepository().get(actionData.getTitle());
        if (movieData == null) {
            return "Error: movie " + movieData.getTitle() + " not found";
        }
        return null;
    }

    @Override
    public String execute() {
        LOGGER.log(Level.INFO, "Rated movie " + actionData.getTitle());
        double givenRating = actionData.getGrade();
        double rating = movieData.getRating();
        int numberOfRatings = movieData.getNumberOfRatings();

        if (numberOfRatings == 0) {
            rating = givenRating;
        } else {
            rating = (rating * numberOfRatings + givenRating) / (numberOfRatings + 1);
        }

        numberOfRatings++;

        movieData.setNumberOfRatings(numberOfRatings);
        movieData.setRating(rating);

        String successMessage = "success -> " + movieData.getTitle()
                                + " was rated with " + rating + " by "
                                + actionData.getUsername();
        return successMessage;
    }
}
