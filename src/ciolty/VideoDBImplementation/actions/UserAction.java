package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

public abstract class UserAction extends VideoDBAction {
    protected final String checkUserValidity(final UserData userData) {
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

    protected final String checkVideoInUser(final UserData userData) {
        String message = checkVideoInUserHistory(userData);
        if (message != null) {
            return message;
        }

        message = checkVideoInUserFavorites(userData);
        if (message != null) {
            return message;
        }
        return null;
    }

    protected final String checkVideoInUserHistory(final UserData userData) {
        if (!userData.getHistory().containsKey(actionData.getTitle())) {
            return "error -> " + actionData.getTitle() + " is not seen";
        }
        return null;
    }

    protected final String checkVideoInUserFavorites(final UserData userData) {
        if (userData.getFavoriteMovies().contains(actionData.getTitle())) {
            return "error -> " + actionData.getTitle() + " is already in favourite list";
        }
        return null;
    }
}
