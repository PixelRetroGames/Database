package ciolty.VideoDBImplementation.actions;

import ciolty.action.Action;

public final class CommandRating extends VideoDBAction {
    private boolean isMovie() {
        return actionData.getSeasonNumber() == 0;
    }

    @Override
    public String execute() {
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
