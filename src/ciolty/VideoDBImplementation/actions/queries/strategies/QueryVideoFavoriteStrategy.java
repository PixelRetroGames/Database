package ciolty.VideoDBImplementation.actions.queries.strategies;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class QueryVideoFavoriteStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(final List<VideoData> videos, final String sortType,
                           final UserRepository userRepository) {
        Map<String, Integer> favorites = userRepository.getAllFavorites();
        videos.removeIf(videoData -> !favorites.containsKey(videoData.getTitle()));
        if (sortType.equals("asc")) {
            videos.sort(new FavoriteComparator(favorites));
        } else {
            videos.sort(new FavoriteComparator(favorites).reversed());
        }
    }

    private static final class FavoriteComparator implements Comparator<VideoData> {
        private final Map<String, Integer> favorites;

        private FavoriteComparator(final Map<String, Integer> favorites) {
            this.favorites = favorites;
        }

        @Override
        public int compare(final VideoData o1, final  VideoData o2) {
            if (favorites.get(o1.getTitle()) == favorites.get(o2.getTitle())) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            return favorites.get(o1.getTitle()) - favorites.get(o2.getTitle());
        }
    }
}
