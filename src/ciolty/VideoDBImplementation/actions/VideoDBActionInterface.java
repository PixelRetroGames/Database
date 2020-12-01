package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.engine.action.Action;

public interface VideoDBActionInterface extends Action {
    @Override
    VideoDBUnitOfWork getUnitOfWork();
}
