package ciolty.action;

import ciolty.factory.Factory;
import ciolty.actionsImplementations.CommandView;

import java.util.Map;

/**
 * Controller pattern: handles action requests by using the Action model
 * and returns the Output
 */
public class ActionController {
    private final Factory<Action> actionFactory;
    private static ActionTypeFormat actionTypeFormat;

    public ActionController() {
        actionFactory = new Factory<Action>(Map.of("command-view", CommandView::new));
        actionTypeFormat = new ActionTypeFormat();
    }

    public Output Execute(ActionData data) {
        String typeName = actionTypeFormat.getFormat(data);
        Action action = actionFactory.get(typeName);

        if (action == null) {
            System.err.println("Action type not found!");
            return null;
        }

        action.setData(data);
        String message = action.execute();
        return new Output(data.getActionId(), message);
    }
}
