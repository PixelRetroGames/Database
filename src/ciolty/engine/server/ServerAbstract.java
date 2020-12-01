package ciolty.engine.server;

import ciolty.engine.action.ActionController;
import ciolty.engine.action.Output;
import ciolty.engine.database.UnitOfWork;

import java.util.List;

public abstract class ServerAbstract implements Server {
    protected List<Output> output;
    protected UnitOfWork unitOfWork;
    protected ActionController actionController;
}
