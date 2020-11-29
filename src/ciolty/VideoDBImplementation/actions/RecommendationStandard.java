package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

public final class RecommendationStandard extends VideoAction {
    @Override
    public String execute() {
        UserData userData = getUserData();
        String message = checkUserValidity(userData);
        if (userData == null) {
            return "StandardRecommendation cannot be applied!";
        }

        String unwatchedMovies = getFirstUnwatchedVideo(userData);
        if (unwatchedMovies == null) {
            return "StandardRecommendation cannot be applied!";
        }
        message = "StandardRecommendation result: " + unwatchedMovies;
        return message;
    }
}
