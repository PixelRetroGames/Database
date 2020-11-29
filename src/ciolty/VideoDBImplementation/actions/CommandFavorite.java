package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.List;
import java.util.logging.Level;

public final class CommandFavorite extends UserAction {
    @Override
    public String execute() {
        LOGGER.log(Level.INFO, "Added " + actionData.getTitle()
                + " to user's " + actionData.getUsername() + " favorites");
                UserData userData = getUserData();

        String message = checkUserValidity(userData);
        if (message != null) {
            return message;
        }

        message = checkVideoInUser(userData);
        if (message != null) {
            return message;
        }

        List<String> favorites = userData.getFavoriteMovies();
        String movieTitle = actionData.getTitle();
        favorites.add(movieTitle);

        message = "success -> " + movieTitle + " was added as favourite";
        return message;
    }
}
