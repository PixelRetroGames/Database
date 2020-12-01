package ciolty.VideoDBImplementation.server;

import ciolty.VideoDBImplementation.actions.commands.CommandFavorite;
import ciolty.VideoDBImplementation.actions.commands.CommandRating;
import ciolty.VideoDBImplementation.actions.commands.CommandView;
import ciolty.VideoDBImplementation.actions.queries.QueryActors;
import ciolty.VideoDBImplementation.actions.queries.QueryMovies;
import ciolty.VideoDBImplementation.actions.queries.QuerySeries;
import ciolty.VideoDBImplementation.actions.queries.QueryUsers;
import ciolty.VideoDBImplementation.actions.recommendations.RecommendationBestUnseen;
import ciolty.VideoDBImplementation.actions.recommendations.RecommendationFavorite;
import ciolty.VideoDBImplementation.actions.recommendations.RecommendationPopular;
import ciolty.VideoDBImplementation.actions.recommendations.RecommendationSearch;
import ciolty.VideoDBImplementation.actions.recommendations.RecommendationStandard;
import ciolty.VideoDBImplementation.actions.VideoDBActionController;
import ciolty.VideoDBImplementation.entities.VideoDBOutput;
import ciolty.engine.action.ActionData;
import ciolty.VideoDBImplementation.entities.VideoDBInput;
import ciolty.engine.action.Output;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.engine.server.ServerAbstract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class VideoDBServer extends ServerAbstract {
    private VideoDBInput input;
    public VideoDBServer(final VideoDBInput input) {
        this.input = input;
        output = new ArrayList<Output>();
        unitOfWork = new VideoDBUnitOfWork();
        unitOfWork.populate(input);

        actionController = new VideoDBActionController(unitOfWork, Map.ofEntries(
            Map.entry("command-view",                 CommandView::new),
            Map.entry("command-favorite",             CommandFavorite::new),
            Map.entry("command-rating",               CommandRating::new),
            Map.entry("recommendation-standard",      RecommendationStandard::new),
            Map.entry("recommendation-favorite",      RecommendationFavorite::new),
            Map.entry("recommendation-search",        RecommendationSearch::new),
            Map.entry("recommendation-best_unseen",   RecommendationBestUnseen::new),
            Map.entry("recommendation-popular",       RecommendationPopular::new),
            Map.entry("query-users",                  QueryUsers::new),
            Map.entry("query-movies",                 QueryMovies::new),
            Map.entry("query-shows",                  QuerySeries::new),
            Map.entry("query-actors",                 QueryActors::new)));
    }

    @Override
    public void runAllActions() {
        for (int i = 0; i < input.getCommands().size(); i++) {
            runAction(i);
        }
    }

    private void runAction(final int position) {
        ActionData actionData = new ActionData(input.getCommands().get(position));
        output.add(new VideoDBOutput(actionData.getActionId(),
                actionController.execute(actionData)));
    }

    @Override
    public List<Output> getOutput() {
        return output;
    }
}
