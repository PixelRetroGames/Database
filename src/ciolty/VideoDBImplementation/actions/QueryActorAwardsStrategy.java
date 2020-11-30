package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;
import ciolty.database.Filter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryActorAwardsStrategy implements QueryActorStrategy {
    @Override
    public void sortVideos(List<ActorData> actors, String sortType,
                           VideoDBUnitOfWork unitOfWork, List<String> awards) {
        final Map<String, Integer> actorsNumberOfAwards = new LinkedHashMap<>();
        for (ActorData actor : actors) {
            int numberOfAwards = 0;
            for (String award : awards) {
                if (actor.getAwards().containsKey(award)) {
                    numberOfAwards++;
                }
            }

            if (numberOfAwards != 0) {
                actorsNumberOfAwards.put(actor.getName(), numberOfAwards);
            }
        }

        if (sortType.equals("asc")) {
            actors.sort(new AwardsComparator(actorsNumberOfAwards));
        } else {
            actors.sort(new  AwardsComparator(actorsNumberOfAwards).reversed());
        }
    }

    private static class AwardsComparator implements java.util.Comparator<ActorData> {
        private final Map<String, Integer> actorsNumberOfAwards;

        private AwardsComparator(Map<String, Integer> actorsNumberOfAwards) {
            this.actorsNumberOfAwards = actorsNumberOfAwards;
        }

        @Override
        public int compare(ActorData o1, ActorData o2) {
            int cmp = Integer.compare(actorsNumberOfAwards.get(o1.getName()),
                    actorsNumberOfAwards.get(o2.getName()));
            if (cmp == 0) {
                return o1.getName().compareTo(o2.getName());
            }
            return cmp;
        }
    }
}
