package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.List;

public interface QueryActorStrategy {
    /**
     * Override for different strategies
     * @param actors
     * @param sortType
     * @param unitOfWork
     * @param awards
     */
    void sortVideos(List<ActorData> actors, String sortType,
                    VideoDBUnitOfWork unitOfWork, List<String> awards);
}
