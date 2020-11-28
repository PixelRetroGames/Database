package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.List;

public final class CommandFavorite extends VideoDBAction {
    @Override
    public String execute() {
        UserData userData = getUserData();

        String message = getUserValidity(userData);
        if (userData == null) {
            return message;
        }

        List<String> favorites = userData.getFavoriteMovies();
        String movieTitle = actionData.getTitle();
        favorites.add(movieTitle);

        message = "success -> " + movieTitle + " was added as favourite";
        return message;
    }
}
