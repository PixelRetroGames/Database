package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.action.ActionAbstract;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class VideoDBAction extends ActionAbstract implements VideoDBActionInterface {
    protected static final Logger LOGGER = Logger.getLogger("VideoDBAction");
    private static final Level LOGGER_LEVEL = Level.WARNING;

    protected VideoDBAction() {
        super();
        LOGGER.setLevel(LOGGER_LEVEL);
    }

    @Override
    public final VideoDBUnitOfWork getUnitOfWork() {
        return (VideoDBUnitOfWork) unitOfWork;
    }
}
