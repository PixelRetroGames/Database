package ciolty.VideoDBImplementation.actions;

import java.util.Map;

public final class CommandView extends UserAction {
    @Override
    public String execute() {
        Map<String, Integer> history = userData.getHistory();
        String movieTitle = actionData.getTitle();

        int timesViewed = 0;
        if (history.containsKey(movieTitle)) {
            timesViewed = history.get(movieTitle);
        }

        timesViewed++;
        history.put(movieTitle, timesViewed);
        String successMessage = "success -> " + movieTitle + " was viewed with total views of "
                                + timesViewed;
        return successMessage;
    }
}
