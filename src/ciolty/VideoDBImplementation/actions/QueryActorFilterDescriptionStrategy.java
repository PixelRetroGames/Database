package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.List;

public final class QueryActorFilterDescriptionStrategy implements QueryActorStrategy {
    @Override
    public void sortVideos(final List<ActorData> actors, final String sortType,
                           final VideoDBUnitOfWork unitOfWork, final List<String> awards) {
        if (sortType.equals("asc")) {
            actors.sort(new NameComparator());
        } else {
            actors.sort(new NameComparator().reversed());
        }
    }

    private static class NameComparator implements java.util.Comparator<ActorData> {
        @Override
        public int compare(final ActorData o1, final ActorData o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
