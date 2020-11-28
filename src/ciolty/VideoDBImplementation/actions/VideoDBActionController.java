package ciolty.VideoDBImplementation.actions;

import ciolty.action.ActionData;
import ciolty.action.Action;
import ciolty.action.ActionControllerAbstract;
import ciolty.database.UnitOfWork;

import java.util.Map;
import java.util.function.Supplier;

public class VideoDBActionController extends ActionControllerAbstract {
    public VideoDBActionController(final UnitOfWork unitOfWork,
                                   final Map<String, Supplier<Action>> actionMap) {
        super(unitOfWork, actionMap, new ActionTypeFormat());
    }

    /**
     * @param data
     * @return result message after executing action
     */
    public final String execute(final ActionData data) {
        String typeName = actionTypeFormat.getFormat(data);
        Action action = actionFactory.get(typeName);

        if (action == null) {
            System.err.println("Action type: " + typeName + " not found!");
            return null;
        }

        action.setActionData(data);
        action.setUnitOfWork(unitOfWork);
        String message = action.execute();
        return message;
    }
}
