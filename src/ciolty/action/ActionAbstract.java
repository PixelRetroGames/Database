package ciolty.action;

public abstract class ActionAbstract implements Action {
    ActionData data;

    @Override
    public void setData(ActionData data) {
        this.data = data;
    }
}
