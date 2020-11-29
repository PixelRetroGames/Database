package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;

import java.util.logging.Level;

public final class CommandRatingMovie extends VideoDBAction {
    private String getMovieValidity() {
        if (movieData == null) {
            return "Error: movie " + movieData.getTitle() + " not found";
        } else {
            return null;
        }
    }

    private MovieData movieData;

    @Override
    public void initLocalData() {
        movieData = getUnitOfWork().getMovieRepository().get(actionData.getTitle());
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

    @Override
    public String checkData() {
        String message = getMovieValidity();
        if (message != null) {
            return message;
        }
        return null;
    }
}
