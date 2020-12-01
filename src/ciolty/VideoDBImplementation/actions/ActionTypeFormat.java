package ciolty.VideoDBImplementation.actions;

import ciolty.engine.action.ActionData;
import ciolty.engine.factory.TypeFormat;

public class ActionTypeFormat implements TypeFormat {
    @Override
    public final String getFormat(final Object object) {
        ActionData actionData = (ActionData) object;
        return actionData.getActionType() + "-" + actionData.getType();
    }
}
