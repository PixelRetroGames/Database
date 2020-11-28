package ciolty.MovieImplementation.actions;

import ciolty.MovieImplementation.MoviesUnitOfWork;
import ciolty.action.ActionAbstract;
import ciolty.MovieImplementation.entities.UserData;
import ciolty.MovieImplementation.repositories.UserRepository;

import java.util.Map;

public class CommandView extends MovieAction {
    @Override
    public String execute() {
        UserData userData = getUserData();

        String message = getUserValidity(userData);
        if (userData == null) {
            return message;
        }

        Map<String, Integer> history = userData.getHistory();
        String movieTitle = actionData.getTitle();

        int timesViewed = 0;
        if (history.containsKey(movieTitle)) {
            timesViewed = history.get(movieTitle);
        }

        timesViewed++;
        history.put(movieTitle, timesViewed);
        message = "success -> " + movieTitle +
                  " was viewed with total views of " + timesViewed;
        return message;
    }
}
