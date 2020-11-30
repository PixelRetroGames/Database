package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.VideoDBImplementation.repositories.VideoDBUnitOfWork;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryActorsAverageStrategy implements QueryActorStrategy {
    @Override
    public void sortVideos(List<ActorData> actors, String sortType,
                           VideoDBUnitOfWork unitOfWork, List<String> awards) {
        actors.forEach(actor -> System.err.println(actor.getName()));
        Map<String, Double> actorsAverage = new LinkedHashMap<>();

        for (ActorData actor : actors) {
            double sum = 0;
            int numberOfRatedVideos = 0;
            System.err.println(actor.getFilmography());
            for (String videoName : actor.getFilmography()) {
                System.err.println(videoName);
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

        actors.removeIf(actor -> !actorsAverage.containsKey(actor.getName()));

        if (sortType.equals("asc")) {
            actors.sort(new AverageComparator(actorsAverage));
        } else {
            actors.sort(new AverageComparator(actorsAverage).reversed());
        }

        actors.forEach(actor -> System.err.println(actor.getName()
                + " " + actorsAverage.get(actor.getName()).toString()));
    }

    private VideoData getVideo(String title, VideoDBUnitOfWork unitOfWork) {
        VideoData video = unitOfWork.getMovieRepository().get(title);
        if (video == null) {
            video = unitOfWork.getSeriesRepository().get(title);
        }
        return video;
    }

    private static class AverageComparator implements java.util.Comparator<ActorData> {
        private final Map<String, Double> actorsAverage;

        private AverageComparator(Map<String, Double> actorsAverage) {
            this.actorsAverage = actorsAverage;
        }

        @Override
        public int compare(ActorData o1, ActorData o2) {
            int cmp = Double.compare(actorsAverage.get(o1.getName()), actorsAverage.get(o2.getName()));
            if (cmp == 0) {
                return o1.getName().compareTo(o2.getName());
            }
            return cmp;
        }
    }
}
