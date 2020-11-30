package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.List;
import java.util.Map;

public class QueryVideoMostViewedStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(List<VideoData> videos, final String sortType,
                                  UserRepository userRepository) {
        final Map<String, Integer> globalHistory = userRepository.getAllHistory();
        videos.removeIf(videoData -> !globalHistory.containsKey(videoData.getTitle()));

        if (sortType.equals("asc")) {
            videos.sort(new MostViewedComparator(globalHistory));
        } else {
            videos.sort(new MostViewedComparator(globalHistory).reversed());
        }
    }

    private static class MostViewedComparator implements java.util.Comparator<VideoData> {
        private final Map<String, Integer> globalHistory;

        private MostViewedComparator(Map<String, Integer> globalHistory) {
            this.globalHistory = globalHistory;
        }

        @Override
        public int compare(VideoData o1, VideoData o2) {
            return globalHistory.get(o1.getTitle()) - globalHistory.get(o2.getTitle());
        }
    }
}
