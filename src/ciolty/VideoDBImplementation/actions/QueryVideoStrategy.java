package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.UserRepository;

import java.util.List;

public interface QueryVideoStrategy {
    /**
     * Sort videos in place
     * @param videos
     * @param sortType
     * @param userRepository
     */
    void sortVideos(List<VideoData> videos, String sortType,
                    UserRepository userRepository);
}
