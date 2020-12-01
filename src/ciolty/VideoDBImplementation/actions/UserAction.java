package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

public abstract class UserAction extends VideoDBAction {
    protected UserData userData;

    /**
     * Initialize userData
     */
    @Override
    public String start() {
        userData = getUserData();
        if (!isUserValid()) {
            return "Invalid user " + actionData.getUsername();
        }
        return null;
    }

    protected final boolean isUserValid() {
        return userData != null;
    }

    protected final UserData getUserData() {
        String username = actionData.getUsername();
        userData = getUnitOfWork().getUserRepository().get(username);
        return userData;
    }

    protected final boolean isVideoInUserHistory() {
        return userData.getHistory().containsKey(actionData.getTitle());
    }

    protected final boolean isVideoInUserFavorites() {
        return userData.getFavoriteMovies().contains(actionData.getTitle());
    }
}
