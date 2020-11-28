package ciolty.action;

/**
 * Model pattern: Interacts with the database and deals with data logic
 */
public interface Action {
    public void setData(ActionData data);
    public String execute();
}
