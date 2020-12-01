package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.Comparator;

public final class RecommendationBestUnseen extends RecommendationNonPremium {
    public RecommendationBestUnseen() {
        super();
        failMessage = "BestRatedUnseenRecommendation cannot be applied!";
    }
    @Override
    public String execute() {
        unwatchedVideos.sort(Comparator.comparingDouble(VideoData::getRating).reversed());
        String successMesage = "BestRatedUnseenRecommendation result: "
                + unwatchedVideos.get(0).getTitle();
        return successMesage;
    }
}
