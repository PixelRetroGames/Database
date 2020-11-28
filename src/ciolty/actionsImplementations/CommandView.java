package ciolty.actionsImplementations;

import ciolty.action.ActionAbstract;

public class CommandView extends ActionAbstract {
    @Override
    public String execute() {
        System.out.println("Command view executed!");
        return "Command view message!";
    }
}
