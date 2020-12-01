package ciolty.VideoDBImplementation.actions.queries.strategies;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.List;
import java.util.Map;

public final class QueryVideoMostViewedStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(final List<VideoData> videos, final String sortType,
                           final UserRepository userRepository) {
        Map<String, Integer> globalHistory = userRepository.getAllHistory();
        videos.removeIf(videoData -> !globalHistory.containsKey(videoData.getTitle()));

        if (sortType.equals("asc")) {
            videos.sort(new MostViewedComparator(globalHistory));
        } else {
            videos.sort(new MostViewedComparator(globalHistory).reversed());
        }
    }

    private static final class MostViewedComparator implements java.util.Comparator<VideoData> {
        private final Map<String, Integer> globalHistory;

        private MostViewedComparator(final Map<String, Integer> globalHistory) {
            this.globalHistory = globalHistory;
        }

        @Override
        public int compare(final VideoData o1, final  VideoData o2) {
            int cmp = globalHistory.get(o1.getTitle()) - globalHistory.get(o2.getTitle());
            if (cmp == 0) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            return cmp;
        }
    }
}
