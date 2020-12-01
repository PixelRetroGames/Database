package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;

public final class CommandRatingVideoStrategy {
    private final VideoData videoData;

    public CommandRatingVideoStrategy(final VideoData videoData) {
        this.videoData = videoData;
    }

    /**
     * Rate video and update rating
     * @param givenRating
     */
    public void rateVideo(final double givenRating) {
        double rating = videoData.getRating();
        int numberOfRatings = videoData.getNumberOfRatings();

        if (numberOfRatings == 0) {
            rating = givenRating;
        } else {
            rating = (rating * numberOfRatings + givenRating) / (numberOfRatings + 1);
        }

        numberOfRatings++;

        videoData.setNumberOfRatings(numberOfRatings);
        videoData.setRating(rating);
    }
}
