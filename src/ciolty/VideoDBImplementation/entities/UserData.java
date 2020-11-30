package ciolty.VideoDBImplementation.entities;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;

public final class UserData {
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favoriteMovies;
    private final ArrayList<String> ratedVideos;

    public UserData(final UserInputData inputData) {
        this.username = inputData.getUsername();
        this.subscriptionType = inputData.getSubscriptionType();
        this.favoriteMovies = inputData.getFavoriteMovies();
        this.history = inputData.getHistory();
        this.ratedVideos = new ArrayList<>();
    }

    public ArrayList<String> getRatedVideos() {
        return ratedVideos;
    }

    public int getNumberOfRatings() {
        return ratedVideos.size();
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
