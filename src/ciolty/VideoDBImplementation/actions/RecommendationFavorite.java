package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.List;

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
        List<String> favoriteVideos = getUnitOfWork().getUserRepository().getAllFavorites();
        for (String video : favoriteVideos) {
            if (!userData.getHistory().containsKey(video)) {
                return video;
            }
        }
        return null;
    }
}
