package ciolty.VideoDBImplementation.actions;

import ciolty.action.Action;

public final class CommandRating extends UserAction {
    private boolean isMovie() {
        return actionData.getSeasonNumber() == 0;
    }

    @Override
    public void initLocalData() {
        super.initLocalData();
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

    @Override
    public String checkData() {
        String message = super.checkData();
        if (message != null) {
            return message;
        }

        message = checkVideoInUserHistory();
        if (message != null) {
            return message;
        }
        return null;
    }
}
