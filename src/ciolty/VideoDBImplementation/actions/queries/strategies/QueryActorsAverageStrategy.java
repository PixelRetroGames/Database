package ciolty.VideoDBImplementation.actions.queries.strategies;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class QueryActorsAverageStrategy implements QueryActorStrategy {
    @Override
    public void sortVideos(final List<ActorData> actors, final String sortType,
                           final VideoDBUnitOfWork unitOfWork, final List<String> awards) {
        Map<String, Double> actorsAverage = getActorsAverage(actors, unitOfWork);
        actors.removeIf(actor -> !actorsAverage.containsKey(actor.getName()));
        if (sortType.equals("asc")) {
            actors.sort(new AverageComparator(actorsAverage));
        } else {
            actors.sort(new AverageComparator(actorsAverage).reversed());
        }
    }

    private Map<String, Double> getActorsAverage(final List<ActorData> actors,
                                                 final VideoDBUnitOfWork unitOfWork) {
        Map<String, Double> actorsAverage = new LinkedHashMap<>();
        for (ActorData actor : actors) {
            double sum = 0;
            int numberOfRatedVideos = 0;
            for (String videoName : actor.getFilmography()) {
                VideoData video = getVideo(videoName, unitOfWork);
                if (video != null && video.getRating() > 0) {
                    sum += video.getRating();
                    numberOfRatedVideos++;
                }
            }
            if (numberOfRatedVideos > 0) {
                actorsAverage.put(actor.getName(), sum / numberOfRatedVideos);
            }
        }
        return actorsAverage;
    }

    private VideoData getVideo(final String title, final VideoDBUnitOfWork unitOfWork) {
        VideoData video = unitOfWork.getMovieRepository().get(title);
        if (video == null) {
            video = unitOfWork.getSeriesRepository().get(title);
        }
        return video;
    }

    private static final class AverageComparator implements java.util.Comparator<ActorData> {
        private final Map<String, Double> actorsAverage;

        private AverageComparator(final Map<String, Double> actorsAverage) {
            this.actorsAverage = actorsAverage;
        }

        @Override
        public int compare(final ActorData o1, final ActorData o2) {
            int cmp = Double.compare(actorsAverage.get(o1.getName()),
                    actorsAverage.get(o2.getName()));
            if (cmp == 0) {
                return o1.getName().compareTo(o2.getName());
            }
            return cmp;
        }
    }
}
