package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;

import java.util.logging.Level;

public final class CommandRatingMovie extends VideoDBAction {
    private String getMovieValidity(final MovieData movieData) {
        if (movieData == null) {
            return "Error: movie " + movieData.getTitle() + " not found";
        } else {
            return null;
        }
    }

    @Override
    public String execute() {
        LOGGER.log(Level.INFO, "Rated movie " + actionData.getTitle());
        MovieData movieData = getUnitOfWork().getMovieRepository().get(actionData.getTitle());
        String message = getMovieValidity(movieData);

        if (movieData == null) {
            return message;
        }

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

        message = "success -> " + movieData.getTitle() + " was rated with "
                  + rating + " by " + actionData.getUsername();
        return message;
    }
}
