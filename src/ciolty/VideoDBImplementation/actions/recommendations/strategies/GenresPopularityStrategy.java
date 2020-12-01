package ciolty.VideoDBImplementation.actions.recommendations.strategies;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;


public final class GenresPopularityStrategy {
    private final VideoDBUnitOfWork unitOfWork;

    public GenresPopularityStrategy(final VideoDBUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    /**
     * @return list of map entries of genres and popularity
     */
    public List<Map.Entry<String, Integer>> getGenresSortedByPopularity() {
        Map<String, Integer> genresPopularity = getMapGenresPopularity();
        ArrayList<Map.Entry<String, Integer>> sortedGenres
                = new ArrayList<>(genresPopularity.entrySet());
        sortedGenres.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortedGenres);
        return sortedGenres;
    }

    private Map<String, Integer> getMapGenresPopularity() {
        Map<String, Integer> allHistory = unitOfWork.getUserRepository().getAllHistory();
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

    private List<String> getVideoGenres(final String title) {
        MovieData movie = unitOfWork.getMovieRepository().get(title);
        SeriesData series = unitOfWork.getSeriesRepository().get(title);

        List<String> genres = null;
        if (movie != null) {
            genres = movie.getGenres();
        } else {
            genres = series.getGenres();
        }

        return genres;
    }
}
