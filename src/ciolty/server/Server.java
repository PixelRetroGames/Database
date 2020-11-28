package ciolty.server;

import ciolty.action.Output;

import java.util.List;

public interface Server {
    public void setInput(Input input);
    public void runAllActions();
    public List<Output> getOutput();
}
