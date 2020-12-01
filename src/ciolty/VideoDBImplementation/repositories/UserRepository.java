package ciolty.VideoDBImplementation.repositories;

import ciolty.database.RepositoryAbstract;
import ciolty.database.ResourceManager;
import ciolty.VideoDBImplementation.entities.UserData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class UserRepository extends RepositoryAbstract<UserData> {
    public UserRepository(final ResourceManager<UserData> resourceManager) {
        super(resourceManager);
    }

    /**
     * @return all users
     */
    public List<UserData> getAll() {
        List<UserData> usersSortedByRating = resourceManager.getAll();
        usersSortedByRating.removeIf(user -> user.getNumberOfRatings() == 0);

        return usersSortedByRating;
    }

    /**
     * @return Map with the global history (history of all users combined)
     */
    public Map<String, Integer> getAllHistory() {
        Map<String, Integer> allHistory = new LinkedHashMap<>();
        for (UserData userData : resourceManager.getAll()) {
            for (Map.Entry<String, Integer> entry : userData.getHistory().entrySet()) {
                int oldValue = 0;
                if (allHistory.containsKey(entry.getKey())) {
                    oldValue = allHistory.get(entry.getKey());
                }
                allHistory.put(entry.getKey(), oldValue + entry.getValue());
            }
        }
        return allHistory;
    }

    /**
     * @return map of favorite movies and how many users have them
     * in their favorite list
     */
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
