package ciolty.VideoDBImplementation.actions;

public final class RecommendationStandard extends RecommendationNonPremium {
    public RecommendationStandard() {
        super();
        failMessage = "StandardRecommendation cannot be applied!";
    }

    @Override
    public String execute() {
        String unwatchedVideo = unwatchedVideos.get(0).getTitle();
        if (unwatchedVideo == null) {
            return failMessage;
        }
        String successMessage = "StandardRecommendation result: "
                                + unwatchedVideo;
        return successMessage;
    }
}
