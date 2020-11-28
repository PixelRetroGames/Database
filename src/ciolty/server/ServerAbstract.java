package ciolty.server;

import ciolty.action.ActionController;
import ciolty.action.Output;
import ciolty.database.UnitOfWork;

import java.util.List;

public abstract class ServerAbstract implements Server {
    protected List<Output> output;
    protected UnitOfWork unitOfWork;
    protected ActionController actionController;
}
