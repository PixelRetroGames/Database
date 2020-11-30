package ciolty.VideoDBImplementation.server;

import ciolty.VideoDBImplementation.actions.CommandFavorite;
import ciolty.VideoDBImplementation.actions.CommandRating;
import ciolty.VideoDBImplementation.actions.CommandView;
import ciolty.VideoDBImplementation.actions.RecommendationBestUnseen;
import ciolty.VideoDBImplementation.actions.RecommendationFavorite;
import ciolty.VideoDBImplementation.actions.RecommendationPopular;
import ciolty.VideoDBImplementation.actions.RecommendationSearch;
import ciolty.VideoDBImplementation.actions.RecommendationStandard;
import ciolty.VideoDBImplementation.actions.VideoDBActionController;
import ciolty.VideoDBImplementation.entities.VideoDBOutput;
import ciolty.action.ActionData;
import ciolty.VideoDBImplementation.entities.VideoDBInput;
import ciolty.action.Output;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.server.ServerAbstract;

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

        actionController = new VideoDBActionController(unitOfWork, Map.of(
            "command-view",                 CommandView::new,
            "command-favorite",             CommandFavorite::new,
            "command-rating",               CommandRating::new,
            "recommendation-standard",      RecommendationStandard::new,
            "recommendation-favorite",      RecommendationFavorite::new,
            "recommendation-search",        RecommendationSearch::new,
            "recommendation-best_unseen",   RecommendationBestUnseen::new,
            "recommendation-popular",       RecommendationPopular::new));
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
