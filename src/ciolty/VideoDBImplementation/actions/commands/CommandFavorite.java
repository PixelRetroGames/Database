package ciolty.VideoDBImplementation.actions.commands;

import ciolty.VideoDBImplementation.actions.UserAction;

import java.util.List;

public final class CommandFavorite extends UserAction {
    @Override
    public String start() {
        String message = super.start();
        if (message != null) {
            return message;
        }
        if (!isVideoInUserHistory()) {
            return "error -> " + actionData.getTitle() + " is not seen";
        }
        if (isVideoInUserFavorites()) {
            return "error -> " + actionData.getTitle() + " is already in favourite list";
        }
        return null;
    }

    @Override
    public String execute() {
        List<String> favorites = userData.getFavoriteMovies();
        String movieTitle = actionData.getTitle();
        favorites.add(movieTitle);

        return "success -> " + movieTitle + " was added as favourite";
    }
}
