package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.action.Action;

public final class CommandRating extends UserAction {
    private boolean isMovie() {
        return actionData.getSeasonNumber() == 0;
    }

    @Override
    public String execute() {
        UserData userData = getUserData();
        String message = checkUserValidity(userData);
        if (message != null) {
            return message;
        }

        message = checkVideoInUserHistory(userData);
        if (message != null) {
            return message;
        }

        Action specializedAction;

        if (isMovie()) {
            specializedAction = new CommandRatingMovie();
        } else {
            specializedAction = new CommandRatingSeries();
        }

        specializedAction.setUnitOfWork(unitOfWork);
        specializedAction.setActionData(actionData);

        return specializedAction.execute();
    }
}
