package ciolty.VideoDBImplementation.actions;

import java.util.Collections;
import java.util.List;

public final class RecommendationSearch extends RecommendationPremium {
    public RecommendationSearch() {
        super();
        setFailMessage("SearchRecommendation cannot be applied!");
    }

    @Override
    public String execute() {
        List<String> videosNames = getUnwatchedVideosOfGenre(userData, actionData.getGenre());
        Collections.sort(videosNames);
        if (videosNames.isEmpty()) {
            return failMessage;
        }

        String successMessage = "SearchRecommendation result: "
                                + videosNames.toString();

        return successMessage;
    }
}
