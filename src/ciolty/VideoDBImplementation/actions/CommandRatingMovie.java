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
        new CommandRatingVideoStrategy(movieData).rateVideo(actionData.getGrade());
        userData.getRatedVideos().add(movieData.getTitle());
        return "success -> " + movieData.getTitle()
                + " was rated with " + actionData.getGrade()
                + " by " + actionData.getUsername();
    }
}
