package ciolty.engine.action;

/**
 *  Class that hides the creation logic for an Action object,
 *  being given its ActionData and executes action.
 */
public interface ActionController {
    /**
     * @param data
     * @return message with the result of action execution
     */
    String execute(ActionData data);
}
