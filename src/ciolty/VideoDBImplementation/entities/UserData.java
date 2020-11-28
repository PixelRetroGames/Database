package ciolty.VideoDBImplementation.entities;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;

public final class UserData {
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favoriteMovies;

    public UserData(final UserInputData inputData) {
        this.username = inputData.getUsername();
        this.subscriptionType = inputData.getSubscriptionType();
        this.favoriteMovies = inputData.getFavoriteMovies();
        this.history = inputData.getHistory();
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }
}
