package ciolty.VideoDBImplementation.actions;

import ciolty.action.Actionable;

public final class CommandRating extends UserAction {
    private boolean isMovie() {
        return actionData.getSeasonNumber() == 0;
    }

    @Override
    public String start() {
        String message = super.start();
        if (message != null) {
            return message;
        }
        if (!isVideoInUserHistory()) {
            return "error -> " + actionData.getTitle() + " is not seen";
        }
        return null;
    }

    @Override
    public String execute() {
        Actionable specializedAction;

        if (isMovie()) {
            specializedAction = new CommandRatingMovie();
        } else {
            specializedAction = new CommandRatingSeries();
        }

        specializedAction.setUnitOfWork(unitOfWork);
        specializedAction.setActionData(actionData);

        return specializedAction.run();
    }
}
