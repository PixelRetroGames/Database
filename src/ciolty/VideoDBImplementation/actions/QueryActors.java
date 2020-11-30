package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.entities.VideoDBOutput;
import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.database.Filter;
import ciolty.factory.Factory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryActors extends VideoDBAction implements ActorAction{
    protected List<String> words;
    protected List<String> awards;

    @Override
    public String start() {
        System.err.println("Actor");
        words = actionData.getFilters().get(2);
        awards = actionData.getFilters().get(3);
        return null;
    }

    protected List<ActorData> getActorsWithFilter() {
        List<ActorData> actors = new ArrayList<>(getAllActorsWithFilter(new ActorFilter(words, awards)));
        return actors;
    }

    @Override
    public String execute() {
        List<String> videosTitles = getActorsNamesWithFilterSortedAndTrimmed();
        return "Query result: " + videosTitles;
    }

    public List<String> getActorsNamesWithFilterSortedAndTrimmed() {
        List<ActorData> actorsWithFilter = getActorsWithFilter();
        Factory<QueryActorStrategy> strategyFactory = new Factory<QueryActorStrategy>(Map.of(
            "average",              QueryActorsAverageStrategy::new,
            "awards",               QueryActorAwardsStrategy::new,
            "filter_description",   QueryActorFilterDescriptionStrategy::new));

        QueryActorStrategy strategy = strategyFactory.get(actionData.getCriteria());
        if (strategy == null) {
            return null;
        }
        strategy.sortVideos(actorsWithFilter, actionData.getSortType(),
                getUnitOfWork(), awards);

        actorsWithFilter.removeAll(actorsWithFilter.
                subList(Math.min(actionData.getNumber(), actorsWithFilter.size()),
                        actorsWithFilter.size()));

        List<String> actorNames = new ArrayList<>();
        actorsWithFilter.forEach(actor -> actorNames.add(actor.getName()));

        return actorNames;
    }

    public class ActorFilter implements Filter {
        protected final List<String> words;
        protected final List<String> awards;

        public ActorFilter(List<String> words, List<String> awards) {
            this.words = words;
            this.awards = awards;
        }

        @Override
        public boolean isValid(final Object object) {
            ActorData actor = (ActorData) object;
            if (awards != null) {
                int numberOfAwards = 0;
                for (String award : awards) {
                    if (actor.getAwards().containsKey(award)) {
                        numberOfAwards++;
                    }
                }
                if (numberOfAwards == 0) {
                    return false;
                }
            }

            if (words != null) {
                for (String word : words) {
                    if (!actor.getCareerDescription().contains(word)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
