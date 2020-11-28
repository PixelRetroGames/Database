package ciolty.MovieImplementation.actions;

import ciolty.MovieImplementation.MoviesUnitOfWork;
import ciolty.MovieImplementation.entities.UserData;
import ciolty.MovieImplementation.repositories.UserRepository;
import ciolty.action.*;
import ciolty.database.UnitOfWork;

import java.util.List;

public class CommandFavorite extends MovieAction {
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
