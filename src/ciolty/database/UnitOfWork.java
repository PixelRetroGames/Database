package ciolty.database;

import ciolty.server.Input;

public interface UnitOfWork {
    public void populate(Input input);
    public void terminate();
}
