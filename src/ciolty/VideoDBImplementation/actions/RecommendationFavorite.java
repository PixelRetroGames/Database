package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.List;
import java.util.Map;

public final class RecommendationFavorite extends RecommendationPremium {
    public RecommendationFavorite() {
        super();
        setFailMessage("FavoriteRecommendation cannot be applied!");
    }

    @Override
    public String execute() {
        String favorite = getFavoriteVideo(userData);
        if (favorite == null) {
            return failMessage;
        }
        return "FavoriteRecommendation result: " + favorite;
    }

    private String getFavoriteVideo(final UserData userData) {
        Map<String, Integer> favoriteVideos = getUnitOfWork().getUserRepository().getAllFavorites();
        String favoriteVideo = null;
        int maxOccurences = 0;
        for (Map.Entry<String, Integer> entry : favoriteVideos.entrySet()) {
            if (!userData.getHistory().containsKey(entry.getKey())) {
                if (entry.getValue() > maxOccurences) {
                    maxOccurences = entry.getValue();
                    favoriteVideo = entry.getKey();
                }
            }
        }
        return favoriteVideo;
    }
}
