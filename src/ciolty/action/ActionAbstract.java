package ciolty.action;

public abstract class ActionAbstract implements Action {
    ActionData data;
    String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setData(ActionData data) {
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
