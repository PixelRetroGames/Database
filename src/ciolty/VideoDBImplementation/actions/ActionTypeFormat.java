package ciolty.VideoDBImplementation.actions;

import ciolty.action.ActionData;
import ciolty.factory.TypeFormat;

public class ActionTypeFormat implements TypeFormat {
    @Override
    public final String getFormat(final Object object) {
        ActionData actionData = (ActionData) object;
        return actionData.getActionType() + "-" + actionData.getType();
    }
}
