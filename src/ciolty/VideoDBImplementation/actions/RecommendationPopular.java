package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.entities.UserData;

import java.security.KeyPair;
import java.util.*;

public final class RecommendationPopular extends VideoAction {
    @Override
    public String execute() {
        UserData userData = getUserData();
        String message = checkUserValidity(userData);
        if (message != null || !userData.getSubscriptionType().equals("PREMIUM")) {
            return "PopularRecommendation cannot be applied!";
        }

        List<Map.Entry<String, Integer>> sortedGenres = getGenresSortedByPopularity();
        boolean found = false;
        int pos = 0;

        message = "PopularRecommendation result: ";
        while (!found && pos < sortedGenres.size()) {
            String genre = sortedGenres.get(pos++).getKey();
            List<String> videos = getUnwatchedVideosOfGenre(userData, genre);
            if (!videos.isEmpty()) {
                message += videos.get(0);
                found = true;
            }
        }

        if (!found) {
            return "PopularRecommendation cannot be applied!";
        }

        return message;
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

    private List<String> getGenres(String title) {
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
