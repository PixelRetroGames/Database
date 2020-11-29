package ciolty.VideoDBImplementation.actions;

import java.util.List;

public final class CommandFavorite extends UserAction {
    @Override
    public String execute() {
        List<String> favorites = userData.getFavoriteMovies();
        String movieTitle = actionData.getTitle();
        favorites.add(movieTitle);

        return "success -> " + movieTitle + " was added as favourite";
    }

    @Override
    public String checkData() {
        String message = super.checkData();
        if (message != null) {
            return message;
        }

        message = checkVideoInUser();
        if (message != null) {
            return message;
        }
        return null;
    }
}
