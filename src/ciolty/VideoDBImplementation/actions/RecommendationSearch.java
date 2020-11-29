package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RecommendationSearch extends RecommendationAction {
    @Override
    public String execute() {
        List<MovieData> unwatchedMovies = getUnwatchedMoviesOfGenre(userData,
                actionData.getGenre());
        List<SeriesData> unwatchedSeries = getUnwatchedSeriesOfGenre(userData,
                actionData.getGenre());

        List<String> videosNames = new ArrayList<>();

        unwatchedMovies.forEach(movie -> videosNames.add(movie.getTitle()));
        unwatchedSeries.forEach(series -> videosNames.add(series.getTitle()));

        Collections.sort(videosNames);

        if (videosNames.isEmpty()) {
            return failMessage;
        }

        String successMessage = "SearchRecommendation result: "
                                + videosNames.toString();

        return successMessage;
    }

    @Override
    public String checkData() {
        failMessage = "SearchRecommendation cannot be applied!";
        checkList.add(userData.getSubscriptionType().equals("PREMIUM") ? null : "notNull");
        return super.checkData();
    }
}
