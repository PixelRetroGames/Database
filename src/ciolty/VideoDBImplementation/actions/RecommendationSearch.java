package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.entities.UserData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RecommendationSearch extends VideoAction {
    @Override
    public String execute() {
        UserData userData = getUserData();
        String message = checkUserValidity(userData);
        if (message != null || !userData.getSubscriptionType().equals("PREMIUM")) {
            return "SearchRecommendation cannot be applied!";
        }

        List<MovieData> unwatchedMovies = getUnwatchedMoviesOfGenre(userData,
                actionData.getGenre());
        List<SeriesData> unwatchedSeries = getUnwatchedSeriesOfGenre(userData,
                actionData.getGenre());

        List<String> videosNames = new ArrayList<>();

        unwatchedMovies.forEach(movie -> videosNames.add(movie.getTitle()));
        unwatchedSeries.forEach(series -> videosNames.add(series.getTitle()));

        Collections.sort(videosNames);

        if (videosNames.isEmpty()) {
            return "SearchRecommendation cannot be applied!";
        }

        message = "SearchRecommendation result: " + videosNames.toString();

        return message;
    }
}
