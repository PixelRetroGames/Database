package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.List;

public abstract class UserAction extends VideoDBAction {
    protected UserData userData;

    /**
     * Initialize userData
     */
    @Override
    public void initLocalData() {
        userData = getUserData();
    }

    /**
     * @return null for success
     */
    @Override
    public String checkData() {
        String message = checkUserValidity();
        if (message != null) {
            return message;
        }
        return null;
    }

    protected final String getFirstNonNull(final List<String> list) {
        for (String str: list) {
            if (str != null) {
                return str;
            }
        }
        return null;
    }

    protected final String checkUserValidity() {
        if (userData == null) {
            return "Error: user " + actionData.getUsername() + " not found";
        } else {
            return null;
        }
    }

    protected final UserData getUserData() {
        String username = actionData.getUsername();
        userData = getUnitOfWork().getUserRepository().get(username);
        return userData;
    }

    protected final String checkVideoInUser() {
        String message = checkVideoInUserHistory();
        if (message != null) {
            return message;
        }

        message = checkVideoInUserFavorites();
        if (message != null) {
            return message;
        }
        return null;
    }

    protected final String checkVideoInUserHistory() {
        if (!userData.getHistory().containsKey(actionData.getTitle())) {
            return "error -> " + actionData.getTitle() + " is not seen";
        }
        return null;
    }

    protected final String checkVideoInUserFavorites() {
        if (userData.getFavoriteMovies().contains(actionData.getTitle())) {
            return "error -> " + actionData.getTitle() + " is already in favourite list";
        }
        return null;
    }
}
