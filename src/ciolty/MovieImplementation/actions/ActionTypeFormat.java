package ciolty.MovieImplementation.actions;

import ciolty.action.ActionData;
import ciolty.factory.TypeFormat;

public class ActionTypeFormat implements TypeFormat {
    @Override
    public String getFormat(Object object) {
        ActionData actionData = (ActionData)object;
        return actionData.getActionType() + "-" + actionData.getType();
    }
}
