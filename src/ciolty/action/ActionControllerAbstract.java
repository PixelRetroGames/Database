package ciolty.action;

import ciolty.database.UnitOfWork;
import ciolty.factory.Factory;
import ciolty.factory.TypeFormat;

import java.util.Map;
import java.util.function.Supplier;

public abstract class ActionControllerAbstract implements ActionController {
    protected final UnitOfWork unitOfWork;
    protected final Factory<Actionable> actionFactory;
    protected static TypeFormat actionTypeFormat;

    protected ActionControllerAbstract(final UnitOfWork unitOfWork,
                                       final Map<String, Supplier<Actionable>> actionMap,
                                       final TypeFormat actionTypeFormat) {
        this.unitOfWork = unitOfWork;
        actionFactory = new Factory<Actionable>(actionMap);
        this.actionTypeFormat = actionTypeFormat;
    }
}
