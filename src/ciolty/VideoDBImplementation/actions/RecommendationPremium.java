package ciolty.VideoDBImplementation.actions;

public abstract class RecommendationPremium extends Recommendation {
    @Override
    public final String start() {
        String message = super.start();
        if (message != null) {
            return failMessage;
        }

        if (!userData.getSubscriptionType().equals("PREMIUM")) {
            return failMessage;
        }

        return null;
    }
}
