package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;

public final class CommandRatingMovie extends UserAction {
    private MovieData movieData;

    @Override
    public String start() {
        String message = super.start();
        if (message != null) {
            return message;
        }

        movieData = getUnitOfWork().getMovieRepository().get(actionData.getTitle());
        if (movieData == null) {
            return "Error: movie " + movieData.getTitle() + " not found";
        }

        if (userData.getRatedVideos().contains(movieData.getTitle())) {
            return "error -> " + movieData.getTitle() + " has been already rated";
        }

        return null;
    }

    @Override
    public String execute() {
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

        userData.getRatedVideos().add(movieData.getTitle());

        return "success -> " + movieData.getTitle() + " was rated with " + givenRating + " by "
                + actionData.getUsername();
    }
}
