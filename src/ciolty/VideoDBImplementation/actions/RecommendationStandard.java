package ciolty.VideoDBImplementation.actions;

public final class RecommendationStandard extends RecommendationAction {
    @Override
    public String execute() {
        String unwatchedMovies = getFirstUnwatchedVideo(userData);
        if (unwatchedMovies == null) {
            return failMessage;
        }
        String successMessage = "StandardRecommendation result: "
                                + unwatchedMovies;
        return successMessage;
    }

    @Override
    public String checkData() {
        failMessage = "StandardRecommendation cannot be applied!";
        return super.checkData();
    }
}
