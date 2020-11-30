package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class QueryVideoFavoriteStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(List<VideoData> videos, final String sortType,
                                  UserRepository userRepository) {
        Map<String, Integer> favorites = userRepository.getAllFavorites();
        videos.removeIf(videoData -> !favorites.containsKey(videoData.getTitle()));
        if (sortType.equals("asc")) {
            videos.sort(new FavoriteComparator(favorites));
        } else {
            videos.sort(new FavoriteComparator(favorites).reversed());
        }
    }

    private static class FavoriteComparator implements Comparator<VideoData> {
        private final Map<String, Integer> favorites;

        private FavoriteComparator(Map<String, Integer> favorites) {
            this.favorites = favorites;
        }

        @Override
        public int compare(VideoData o1, VideoData o2) {
            return favorites.get(o1.getTitle()) - favorites.get(o2.getTitle());
        }
    }
}
