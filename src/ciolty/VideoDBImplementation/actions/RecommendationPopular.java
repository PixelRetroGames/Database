package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public final class RecommendationPopular extends RecommendationAction {
    @Override
    public String execute() {
        List<Map.Entry<String, Integer>> sortedGenres = getGenresSortedByPopularity();
        boolean found = false;
        int pos = 0;

        String successMessage = "PopularRecommendation result: ";
        while (!found && pos < sortedGenres.size()) {
            String genre = sortedGenres.get(pos++).getKey();
            List<String> videos = getUnwatchedVideosOfGenre(userData, genre);
            if (!videos.isEmpty()) {
                successMessage += videos.get(0);
                found = true;
            }
        }

        if (!found) {
            return failMessage;
        }

        return successMessage;
    }

    @Override
    public String checkData() {
        failMessage = "PopularRecommendation cannot be applied!";
        checkList.add(userData.getSubscriptionType().equals("PREMIUM") ? null : "notNull");
        return super.checkData();
    }

    private List<Map.Entry<String, Integer>> getGenresSortedByPopularity() {
        Map<String, Integer> allHistory = getUnitOfWork().getUserRepository().getAllHistory();
        Map<String, Integer> genresPopularity = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : allHistory.entrySet()) {
            List<String> genres = getGenres(entry.getKey());
            for (String genre : genres) {
                int popularity = 0;
                if (genresPopularity.containsKey(genre)) {
                    popularity = genresPopularity.get(genre);
                }
                popularity += entry.getValue();
                genresPopularity.put(genre, popularity);
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedGenres
                = new ArrayList<>(genresPopularity.entrySet());
        sortedGenres.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortedGenres);
        return sortedGenres;
    }

    private List<String> getGenres(final String title) {
        MovieData movie = getUnitOfWork().getMovieRepository().get(title);
        SeriesData series = getUnitOfWork().getSeriesRepository().get(title);

        List<String> genres = null;
        if (movie != null) {
            genres = movie.getGenres();
        } else {
            genres = series.getGenres();
        }

        return genres;
    }
}
