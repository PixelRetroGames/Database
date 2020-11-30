package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;


public abstract class GenresPopularity extends RecommendationPremium {
    /**
     * @return all genres in order of popularity
     */
    public final List<Map.Entry<String, Integer>> getGenresSortedByPopularity() {
        Map<String, Integer> genresPopularity = getMapGenresPopularity();
        ArrayList<Map.Entry<String, Integer>> sortedGenres
                = new ArrayList<>(genresPopularity.entrySet());
        sortedGenres.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortedGenres);
        return sortedGenres;
    }

    /**
     * @return map of genres and popularity
     */
    public final  Map<String, Integer> getMapGenresPopularity() {
        Map<String, Integer> allHistory = getUnitOfWork().getUserRepository().getAllHistory();
        Map<String, Integer> genresPopularity = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : allHistory.entrySet()) {
            List<String> genres = getVideoGenres(entry.getKey());
            for (String genre : genres) {
                int popularity = entry.getValue();
                if (genresPopularity.containsKey(genre)) {
                    popularity += genresPopularity.get(genre);
                }
                genresPopularity.put(genre, popularity);
            }
        }

        return genresPopularity;
    }

    /**
     * @param title
     * @return genres of the video with title
     */
    public final  List<String> getVideoGenres(final String title) {
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
