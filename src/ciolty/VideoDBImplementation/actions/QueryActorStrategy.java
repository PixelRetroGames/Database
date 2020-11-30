package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.List;

public interface QueryActorStrategy {
    public void sortVideos(List<ActorData> actors, String sortType,
                           VideoDBUnitOfWork unitOfWork, List<String> awards);
}
