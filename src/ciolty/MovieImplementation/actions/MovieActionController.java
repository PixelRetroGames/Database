package ciolty.MovieImplementation.actions;

import ciolty.action.ActionData;
import ciolty.action.Action;
import ciolty.action.ActionControllerAbstract;
import ciolty.database.UnitOfWork;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Controller pattern: handles action requests by using the Action model
 * and returns the Output
 */
public class MovieActionController extends ActionControllerAbstract {
    public MovieActionController(UnitOfWork unitOfWork, Map<String, Supplier<Action>> actionMap) {
        super(unitOfWork, actionMap, new ActionTypeFormat());
    }

    public String execute(ActionData data) {
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
