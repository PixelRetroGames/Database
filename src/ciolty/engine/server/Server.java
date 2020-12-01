package ciolty.engine.server;

import ciolty.engine.action.Output;

import java.util.List;

/**
 *  Hides the logic for setting up the action controller and running actions
 */
public interface Server {
    /**
     * Runs all actions and pushes results in List of Output
     */
    void runAllActions();

    /**
     * @return List of outputs resulted from running
     */
    List<Output> getOutput();
}
