package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;

import java.util.Comparator;
import java.util.List;

public final class RecommendationBestUnseen extends RecommendationAction {
    private List<MovieData> unwatchedMovies;

    @Override
    public void initLocalData() {
        super.initLocalData();
        unwatchedMovies = getUnwatchedMovies(userData);
    }

    @Override
    public String execute() {
        unwatchedMovies.sort(Comparator.comparingDouble(MovieData::getRating).reversed());
        String successMesage = "BestRatedUnseenRecommendation result: "
                + unwatchedMovies.get(0).getTitle();
        return successMesage;
    }

    @Override
    public String checkData() {
        failMessage = "Best_unseenRecommendation cannot be applied!";
        checkList.add(!unwatchedMovies.isEmpty() ? null : "notNull");
        return super.checkData();
    }
}
