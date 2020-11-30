package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;

public final class QueryVideoLongestStrategy implements QueryVideoStrategy{
    @Override
    public void sortVideos(List<VideoData> videos, final String sortType,
                                  UserRepository userRepository) {
       if (sortType.equals("asc")) {
            videos.sort(Comparator.comparingInt(VideoData::getDuration));
        } else {
            videos.sort(Comparator.comparingInt(VideoData::getDuration).reversed());
        }
    }
}
