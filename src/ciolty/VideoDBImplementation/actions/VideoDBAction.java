package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.action.ActionAbstract;

public abstract class VideoDBAction extends ActionAbstract implements VideoDBActionInterface {
    protected VideoDBAction() {
        super();
    }

    @Override
    public final VideoDBUnitOfWork getUnitOfWork() {
        return (VideoDBUnitOfWork) unitOfWork;
    }
}
