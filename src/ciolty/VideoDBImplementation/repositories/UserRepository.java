package ciolty.VideoDBImplementation.repositories;

import ciolty.database.RepositoryAbstract;
import ciolty.database.ResourceManager;
import ciolty.VideoDBImplementation.entities.UserData;

import java.util.*;

public final class UserRepository extends RepositoryAbstract<UserData> {
    public UserRepository(final ResourceManager<UserData> resourceManager) {
        super(resourceManager);
    }

    public List<UserData> getTopUsersSortedByRating(int numberOfUsers) {
        List<UserData> usersSortedByRating = resourceManager.getAll();
        usersSortedByRating.removeIf(user -> user.getNumberOfRatings() == 0);
        usersSortedByRating.sort(Comparator.comparingInt(UserData::getNumberOfRatings).reversed());
        usersSortedByRating.removeAll(usersSortedByRating.subList(
                Math.min(numberOfUsers, usersSortedByRating.size()),
                usersSortedByRating.size()));

        return usersSortedByRating;
    }

    /**
     * @return Map with the global history (history of all users combined)
     */
    public Map<String, Integer> getAllHistory() {
        Map<String, Integer> allHistory = new LinkedHashMap<>();
        for (UserData userData : resourceManager.getAll()) {
            allHistory.putAll(userData.getHistory());
        }
        return allHistory;
    }

    public Map<String, Integer> getAllFavorites() {
        Map<String, Integer> allFavorites = new LinkedHashMap<>();
        for (UserData userData : resourceManager.getAll()) {
            for (String movie : userData.getFavoriteMovies()) {
                int occurences = 0;
                if (allFavorites.containsKey(movie)) {
                   occurences = allFavorites.get(movie);
                }
                allFavorites.put(movie, occurences + 1);
            }
        }
        return allFavorites;
    }
}
