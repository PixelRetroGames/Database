package ciolty.engine.action;

import ciolty.engine.database.UnitOfWork;

/**
 * Model pattern: Interacts with the database and deals with data logic
 * Interface used by action controller
 */
public interface Actionable {
    /**
     * Link action to UnitOfWork in order to be able to communicate
     * with the Repositories.
     * @param unitOfWork
     */
    void setUnitOfWork(UnitOfWork unitOfWork);

    /**
     * @return unit of work
     */
    UnitOfWork getUnitOfWork();

    /**
     * After creating object through factory set its data.
     * @param actionData
     */
    void setActionData(ActionData actionData);

    /**
     * The function that the ActionController will call at execution
     * @return message with the result of operation
     */
    String run();
}
