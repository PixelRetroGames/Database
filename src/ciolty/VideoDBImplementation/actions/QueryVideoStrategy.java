package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.List;

public interface QueryVideoStrategy {
    public void sortVideos(List<VideoData> videos, final String sortType,
                           UserRepository userRepository);
}
