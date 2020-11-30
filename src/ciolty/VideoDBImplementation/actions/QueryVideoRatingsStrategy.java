package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;

public final class QueryVideoRatingsStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(List<VideoData> videos, final String sortType,
                                  UserRepository userRepository) {
        videos.removeIf(videoData -> Math.abs(videoData.getRating()) < 1E-7);
        if (sortType.equals("asc")) {
            videos.sort(new RatingComparator());
        } else {
            videos.sort(new RatingComparator().reversed());
        }
    }

    private static class RatingComparator implements Comparator<VideoData> {
        @Override
        public int compare(VideoData o1, VideoData o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    }
}
