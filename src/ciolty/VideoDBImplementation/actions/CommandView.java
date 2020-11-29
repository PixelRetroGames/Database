package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.Map;

public final class CommandView extends UserAction {
    @Override
    public String execute() {
        UserData userData = getUserData();

        String message = checkUserValidity(userData);
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
        message = "success -> " + movieTitle
                  + " was viewed with total views of " + timesViewed;
        return message;
    }
}
