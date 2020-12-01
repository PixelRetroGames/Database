package ciolty.VideoDBImplementation.actions.recommendations;

import ciolty.VideoDBImplementation.actions.UserAction;
import ciolty.VideoDBImplementation.actions.VideoAction;

public abstract class Recommendation extends UserAction implements VideoAction {
    protected String failMessage = "FAIL MESSAGE NOT SET";

    public final void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }
}
