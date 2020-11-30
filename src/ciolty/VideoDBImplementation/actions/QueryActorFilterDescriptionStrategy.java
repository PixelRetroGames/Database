package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class QueryActorFilterDescriptionStrategy implements QueryActorStrategy {
    @Override
    public void sortVideos(List<ActorData> actors, String sortType, VideoDBUnitOfWork unitOfWork, List<String> awards) {
        if (sortType.equals("asc")) {
            actors.sort(new NameComparator());
        } else {
            actors.sort(new NameComparator().reversed());
        }
    }

    private static class NameComparator implements java.util.Comparator<ActorData> {
        @Override
        public int compare(ActorData o1, ActorData o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
