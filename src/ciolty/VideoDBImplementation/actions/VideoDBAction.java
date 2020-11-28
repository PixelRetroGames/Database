package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.action.ActionAbstract;

public abstract class VideoDBAction extends ActionAbstract {
    public final VideoDBUnitOfWork getUnitOfWork() {
        return (VideoDBUnitOfWork) unitOfWork;
    }

    protected final String getUserValidity(final UserData userData) {
        if (userData == null) {
            return "Error: user " + actionData.getUsername() + " not found";
        } else {
            return null;
        }
    }

    protected final UserData getUserData() {
        String username = actionData.getUsername();
        UserData userData = getUnitOfWork().getUserRepository().get(username);
        return userData;
    }
}
