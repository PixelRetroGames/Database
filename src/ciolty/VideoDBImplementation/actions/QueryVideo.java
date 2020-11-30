package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;
import ciolty.database.Filter;
import ciolty.factory.Factory;

import java.util.*;

public abstract class QueryVideo extends VideoDBAction {
    protected int year;
    protected String genre;
    @Override
    public String start() {
        year = actionData.getFilters().get(0).get(0) == null ? 0 : Integer.parseInt(actionData.getFilters().get(0).get(0));
        genre = actionData.getFilters().get(1).get(0);
        return null;
    }

    public List<String> getVideosTitlesWithFilterSortedAndTrimmed() {
        List<VideoData> videosWithFilter = getVideosWithFilter();
        Factory<QueryVideoStrategy> strategyFactory = new Factory<QueryVideoStrategy>(Map.of(
                "longest", QueryVideoLongestStrategy::new,
                "ratings", QueryVideoRatingsStrategy::new,
                "most_viewed", QueryVideoMostViewedStrategy::new,
                "favorite", QueryVideoFavoriteStrategy::new
        ));

        QueryVideoStrategy strategy = strategyFactory.get(actionData.getCriteria());
        strategy.sortVideos(videosWithFilter, actionData.getSortType(),
                getUnitOfWork().getUserRepository());

        videosWithFilter.removeAll(videosWithFilter.
                subList(Math.min(actionData.getNumber(), videosWithFilter.size()),
                        videosWithFilter.size()));

        List<String> videosTitles = new ArrayList<>();
        videosWithFilter.forEach(video->videosTitles.add(video.getTitle()));

        return videosTitles;
    }

    protected abstract List<VideoData> getVideosWithFilter();

    public class VideoFilter implements Filter {
        private final int year;
        private final String genre;

        public VideoFilter(int year, String genre) {
            this.year = year;
            this.genre = genre;
        }

        @Override
        public boolean isValid(final Object object) {
            VideoData videoData = (VideoData) object;
            if (year != 0 && videoData.getYear() != year) {
                return false;
            }
            if (genre != null && !videoData.getGenres().contains(genre)) {
                return false;
            }
            return true;
        }
    }
}
