package ciolty.VideoDBImplementation.actions.recommendations;

import ciolty.VideoDBImplementation.actions.recommendations.strategies.GenresPopularityStrategy;

import java.util.List;
import java.util.Map;

public final class RecommendationPopular extends RecommendationPremium {
    public RecommendationPopular() {
        super();
        setFailMessage("PopularRecommendation cannot be applied!");
    }

    @Override
    public String execute() {
        String popularRecommendation = getPopularRecommendation();
        if (popularRecommendation == null) {
            return failMessage;
        }
        return "PopularRecommendation result: " + popularRecommendation;
    }

    private String getPopularRecommendation() {
        List<Map.Entry<String, Integer>> sortedGenres =
                new GenresPopularityStrategy(getUnitOfWork()).getGenresSortedByPopularity();
        for (Map.Entry<String, Integer> genreEntry : sortedGenres) {
            String genre = genreEntry.getKey();
            List<String> videos = getUnwatchedVideosOfGenre(userData, genre);
            if (!videos.isEmpty()) {
                return videos.get(0);
            }
        }
        return null;
    }
}
