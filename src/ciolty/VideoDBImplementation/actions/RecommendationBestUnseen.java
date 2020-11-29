package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.UserData;

import java.util.Comparator;
import java.util.List;

public final class RecommendationBestUnseen extends VideoAction {
    @Override
    public String execute() {
        UserData userData = getUserData();
        String message = checkUserValidity(userData);
        if (message != null) {
            return "Best_unseenRecommendation cannot be applied!";
        }

        List<MovieData> unwatchedMovies = getUnwatchedMovies(userData);
        unwatchedMovies.sort(Comparator.comparingDouble(MovieData::getRating).reversed());

        if (unwatchedMovies.isEmpty()) {
            return "Best_unseenRecommendation cannot be applied!";
        }

        message = "BestRatedUnseenRecommendation result: " + unwatchedMovies.get(0).getTitle();
        return message;
    }
}
