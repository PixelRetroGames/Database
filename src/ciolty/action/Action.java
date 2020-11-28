package ciolty.action;

import ciolty.database.UnitOfWork;

/**
 * Model pattern: Interacts with the database and deals with data logic
 */
public interface Action {
    public void setUnitOfWork(UnitOfWork unitOfWork);
    public void setActionData(ActionData actionData);
    public String execute();
}
