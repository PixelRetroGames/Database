package ciolty.VideoDBImplementation.actions;

import java.util.ArrayList;

public abstract class RecommendationAction extends VideoAction {
    protected String failMessage = "NO FAIL MESSAGE PROVIDED!";
    protected final ArrayList<String> checkList = new ArrayList<>();

    /**
     * When overriding add elements to checklist and call super
     * @return fail message or null for success
     */
    @Override
    public String checkData() {
        checkList.add(0, checkUserValidity());
        if (getFirstNonNull(checkList) != null) {
            return failMessage;
        }

        return null;
    }
}
