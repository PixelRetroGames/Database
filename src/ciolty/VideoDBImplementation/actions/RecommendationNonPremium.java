package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.List;

public abstract class RecommendationNonPremium extends Recommendation {
    protected List<VideoData> unwatchedVideos;

    @Override
    public final String start() {
        System.err.println("Start called");
        String message = super.start();
        if (message != null) {
            return failMessage;
        }

        unwatchedVideos = getUnwatchedVideos(userData);
        if (unwatchedVideos.isEmpty()) {
            return failMessage;
        }
        return null;
    }
}
