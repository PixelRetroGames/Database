package ciolty.VideoDBImplementation.actions.recommendations;

import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.List;

public abstract class RecommendationNonPremium extends Recommendation {
    protected List<VideoData> unwatchedVideos;

    @Override
    public final String start() {
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
