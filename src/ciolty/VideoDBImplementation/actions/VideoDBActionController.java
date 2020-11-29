package ciolty.VideoDBImplementation.actions;

import ciolty.action.ActionData;
import ciolty.action.Action;
import ciolty.action.ActionControllerAbstract;
import ciolty.database.UnitOfWork;

import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VideoDBActionController extends ActionControllerAbstract {
    protected static final Logger LOGGER = Logger.getLogger("VideoDBActionController");
    private static final Level LOGGER_LEVEL = Level.OFF;

    public VideoDBActionController(final UnitOfWork unitOfWork,
                                   final Map<String, Supplier<Action>> actionMap) {
        super(unitOfWork, actionMap, new ActionTypeFormat());

        LOGGER.setLevel(LOGGER_LEVEL);
    }

    /**
     * @param data
     * @return result message after executing action
     */
    public final String execute(final ActionData data) {
        String typeName = actionTypeFormat.getFormat(data);
        Action action = actionFactory.get(typeName);

        if (action == null) {
            LOGGER.log(Level.WARNING, "Action type: " + typeName + " not found!");
            return null;
        }

        action.setUnitOfWork(unitOfWork);
        action.setActionData(data);
        String message = action.run();
        return message;
    }
}
