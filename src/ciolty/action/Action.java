package ciolty.action;

import ciolty.database.UnitOfWork;

/**
 * Model pattern: Interacts with the database and deals with data logic
 */
public interface Action extends Actionable {
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
     * Called after setting the data
     */
    void initLocalData();

    /**
     * The function that the ActionController will call at execution
     * after checking data.
     * @return message with the result of operation
     */
    String execute();

    /**
     * Function called before execute. Execute will be called only
     * if checkData returns null, else the string will be returned
     * from the run function too.
     * @return null if data is valid
     */
    String checkData();
}
