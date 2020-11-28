package ciolty.MovieImplementation.server;

import ciolty.MovieImplementation.actions.MovieActionController;
import ciolty.MovieImplementation.entities.MovieOutput;
import ciolty.action.ActionData;
import ciolty.MovieImplementation.entities.MovieInput;
import ciolty.action.Output;
import ciolty.MovieImplementation.actions.CommandFavorite;
import ciolty.MovieImplementation.actions.CommandView;
import ciolty.MovieImplementation.MoviesUnitOfWork;
import ciolty.server.ServerAbstract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieServer extends ServerAbstract {
    public MovieServer(MovieInput input) {
        setInput(input);
        output = new ArrayList<Output>();
        unitOfWork = new MoviesUnitOfWork();
        unitOfWork.populate(input);

        actionController = new MovieActionController(unitOfWork, Map.of(
                "command-view",      CommandView::new,
                "command-favorite",  CommandFavorite::new));
    }

    @Override
    public void runAllActions() {
        for (int i = 0; i < ((MovieInput)input).getCommands().size(); i++) {
            runAction(i);
        }
    }

    private void runAction(int i) {
        ActionData actionData = new ActionData(((MovieInput)input).getCommands().get(i));
        output.add(new MovieOutput(actionData.getActionId(), actionController.execute(actionData)));
    }

    @Override
    public List<Output> getOutput() {
        return output;
    }
}
