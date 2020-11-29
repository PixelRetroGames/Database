package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.database.Filter;

import java.util.List;
import java.util.logging.Level;

public final class RecommendationFavorite extends UserAction {
    @Override
    public String execute() {
        UserData userData = getUserData();
        String message = checkUserValidity(userData);
        if (userData == null || !userData.getSubscriptionType().equals("PREMIUM")) {
            return "FavoriteRecommendation cannot be applied!";
        }

        String favorite = getFavoriteVideo(userData);

        if (favorite == null) {
            return "FavoriteRecommendation cannot be applied!";
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
