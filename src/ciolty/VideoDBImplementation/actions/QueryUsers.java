package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueryUsers extends VideoDBAction {
    @Override
    public String start() {
        return null;
    }

    @Override
    public String execute() {
        List<UserData> topUsers = getUnitOfWork().getUserRepository()
                .getTopUsersSortedByRating(actionData.getNumber());
        topUsers.sort(Comparator.comparingInt(UserData::getNumberOfRatings));

        List<String> topUsersSortedByRating = new ArrayList<>();
        topUsers.forEach(user -> topUsersSortedByRating.add(user.getUsername()));
        return "Query result: " + topUsersSortedByRating.toString();
    }
}
