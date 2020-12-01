package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.database.Filter;
import ciolty.factory.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class QueryActors extends VideoDBAction implements ActorAction {
    protected List<String> words;
    protected List<String> awards;

    @Override
    public String start() {
        words = actionData.getFilters().get(2);
        awards = actionData.getFilters().get(3);
        return null;
    }

    @Override
    public String execute() {
        List<String> videosTitles = getActorsNamesWithFilterSortedAndTrimmed();
        return "Query result: " + videosTitles;
    }

    private List<ActorData> getActorsWithFilter() {
        List<ActorData> actors = new ArrayList<>(getAllActorsWithFilter(
                new ActorFilter(words, awards)));
        return actors;
    }

    private List<String> getActorsNamesWithFilterSortedAndTrimmed() {
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

    public static final class ActorFilter implements Filter {
        protected final List<String> words;
        protected final List<String> awards;

        public ActorFilter(final List<String> words, final List<String> awards) {
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
                    } else {
                        numberOfAwards = 0;
                        break;
                    }
                }
                if (numberOfAwards == 0) {
                    return false;
                }
            }

            if (words != null) {
                List<String> descriptionWords = getWords(actor.getCareerDescription()
                        .toLowerCase());
                for (String word : words) {
                    if (!descriptionWords.contains(word)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private List<String> getWords(final String sentence) {
            final String[] wordsArray = sentence.split("\\W+");
            return new ArrayList<String>(Arrays.asList(wordsArray));
        }
    }
}
