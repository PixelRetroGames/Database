package ciolty.VideoDBImplementation.actions.queries.strategies;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;

public final class QueryVideoRatingsStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(final List<VideoData> videos, final String sortType,
                           final UserRepository userRepository) {
        videos.removeIf(videoData -> Math.abs(videoData.getRating()) < 1E-7);
        if (sortType.equals("asc")) {
            videos.sort(new RatingComparator());
        } else {
            videos.sort(new RatingComparator().reversed());
        }
    }

    private static final class RatingComparator implements Comparator<VideoData> {
        @Override
        public int compare(final VideoData o1, final VideoData o2) {
            int cmp = Double.compare(o1.getRating(), o2.getRating());
            if (cmp == 0) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            return cmp;
        }
    }
}
