package ciolty.VideoDBImplementation.actions;

import ciolty.engine.action.ActionData;
import ciolty.engine.action.ActionControllerAbstract;
import ciolty.engine.action.Actionable;
import ciolty.engine.database.UnitOfWork;

import java.util.Map;
import java.util.function.Supplier;


public class VideoDBActionController extends ActionControllerAbstract {
    public VideoDBActionController(final UnitOfWork unitOfWork,
                                   final Map<String, Supplier<Actionable>> actionMap) {
        super(unitOfWork, actionMap, new ActionTypeFormat());
    }

    /**
     * @param data
     * @return result message after executing action
     */
    public final String execute(final ActionData data) {
        String typeName = actionTypeFormat.getFormat(data);
        Actionable action = actionFactory.get(typeName);

        if (action == null) {
            return null;
        }

        action.setUnitOfWork(unitOfWork);
        action.setActionData(data);
        String message = action.run();
        return message;
    }
}
