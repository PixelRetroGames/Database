package ciolty.VideoDBImplementation.actions.queries;

import ciolty.VideoDBImplementation.actions.VideoDBAction;
import ciolty.VideoDBImplementation.entities.UserData;
import java.util.ArrayList;
import java.util.List;

public final class QueryUsers extends VideoDBAction {
    @Override
    public String start() {
        return null;
    }

    @Override
    public String execute() {
        List<UserData> topUsers = getUnitOfWork().getUserRepository()
                .getAll();
        if (actionData.getSortType().equals("asc")) {
            topUsers.sort(new UserComparator());
        } else {
            topUsers.sort(new UserComparator().reversed());
        }

        if (actionData.getNumber() != 0) {
            topUsers.removeAll(topUsers.subList(
                    Math.min(actionData.getNumber(), topUsers.size()),
                    topUsers.size()));
        }

        List<String> topUsersSortedByRating = new ArrayList<>();
        topUsers.forEach(user -> topUsersSortedByRating.add(user.getUsername()));
        return "Query result: " + topUsersSortedByRating.toString();
    }

    private static class UserComparator implements java.util.Comparator<UserData> {
        @Override
        public int compare(final UserData o1, final UserData o2) {
            if (o1.getNumberOfRatings() == o2.getNumberOfRatings()) {
                return o1.getUsername().compareTo(o2.getUsername());
            }
            return o1.getNumberOfRatings() - o2.getNumberOfRatings();
        }
    }
}
