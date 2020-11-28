package ciolty.MovieImplementation.actions;

import ciolty.MovieImplementation.MoviesUnitOfWork;
import ciolty.MovieImplementation.entities.UserData;
import ciolty.action.ActionAbstract;

public abstract class MovieAction extends ActionAbstract {
    public MoviesUnitOfWork getUnitOfWork() {
        return (MoviesUnitOfWork)unitOfWork;
    }

    public String getUserValidity(UserData userData) {
        if (userData == null) {
            return "Error: user " + actionData.getUsername() + " not found";
        } else {
            return null;
        }
    }

    protected UserData getUserData() {
        String username = actionData.getUsername();
        UserData userData = getUnitOfWork().userRepository.get(username);
        return userData;
    }
}
