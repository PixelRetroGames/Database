package ciolty.database;

import ciolty.server.Input;

public interface UnitOfWork {
    /**
     * Populates repositories with data from input
     * @param input
     */
    void populate(Input input);

    /**
     * Called when it is safe to save the state of the databse
     */
    void terminate();
}
