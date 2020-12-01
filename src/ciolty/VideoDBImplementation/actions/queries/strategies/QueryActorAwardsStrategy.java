package ciolty.VideoDBImplementation.actions.queries.strategies;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import java.util.List;

public final class QueryActorAwardsStrategy implements QueryActorStrategy {
    @Override
    public void sortVideos(final List<ActorData> actors, final String sortType,
                           final VideoDBUnitOfWork unitOfWork, final List<String> awards) {
        if (sortType.equals("asc")) {
            actors.sort(new AwardsComparator());
        } else {
            actors.sort(new  AwardsComparator().reversed());
        }
    }

    private static class AwardsComparator implements java.util.Comparator<ActorData> {
        @Override
        public int compare(final ActorData o1, final ActorData o2) {
            int cmp = Integer.compare(o1.getNumberOfAwards(),
                    o2.getNumberOfAwards());
            if (cmp == 0) {
                return o1.getName().compareTo(o2.getName());
            }
            return cmp;
        }
    }
}
