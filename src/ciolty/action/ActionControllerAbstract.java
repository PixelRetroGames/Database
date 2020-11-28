package ciolty.action;

import ciolty.database.UnitOfWork;
import ciolty.factory.Factory;
import ciolty.factory.TypeFormat;

import java.util.Map;
import java.util.function.Supplier;

public abstract class ActionControllerAbstract implements ActionController {
    protected final UnitOfWork unitOfWork;
    protected final Factory<Action> actionFactory;
    protected static TypeFormat actionTypeFormat;

    protected ActionControllerAbstract(UnitOfWork unitOfWork, Map<String, Supplier<Action>> actionMap,
                                    TypeFormat actionTypeFormat) {
        this.unitOfWork = unitOfWork;
        actionFactory = new Factory<Action>(actionMap);
        this.actionTypeFormat = actionTypeFormat;
    }
}
