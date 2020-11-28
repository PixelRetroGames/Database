package ciolty.actionsImplementations;

import ciolty.action.ActionAbstract;

public class CommandView extends ActionAbstract {
    @Override
    public void execute() {
        System.out.println("Command view executed!");
        setMessage("Command view message!");
    }
}
