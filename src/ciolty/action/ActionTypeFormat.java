package ciolty.action;

import ciolty.factory.TypeFormat;

public class ActionTypeFormat implements TypeFormat<ActionData> {

    @Override
    public String getFormat(ActionData object) {
        return object.getActionType() + "-" + object.getType();
    }
}
