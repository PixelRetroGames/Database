package ciolty.VideoDBImplementation.actions;

public abstract class Recommendation extends UserAction implements VideoAction {
    protected String failMessage = "FAIL MESSAGE NOT SET";

    public final void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }
}
