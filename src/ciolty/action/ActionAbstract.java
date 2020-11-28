package ciolty.action;

import ciolty.database.UnitOfWork;

public abstract class ActionAbstract implements Action {
    protected UnitOfWork unitOfWork;
    protected ActionData actionData;

    @Override
    public final void setUnitOfWork(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public final void setActionData(ActionData actionData) {
        this.actionData = actionData;
    }
}
