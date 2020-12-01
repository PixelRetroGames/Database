package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;

public final class QueryVideoLongestStrategy implements QueryVideoStrategy {
    @Override
    public void sortVideos(final List<VideoData> videos, final String sortType,
                           final UserRepository userRepository) {
       if (sortType.equals("asc")) {
            videos.sort(new FavoriteComparator());
        } else {
            videos.sort(new FavoriteComparator().reversed());
        }
    }

    private static final class FavoriteComparator implements Comparator<VideoData> {
        @Override
        public int compare(final VideoData o1, final  VideoData o2) {
            if (o1.getDuration() == o2.getDuration()) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            return o1.getDuration() - o2.getDuration();
        }
    }
}
