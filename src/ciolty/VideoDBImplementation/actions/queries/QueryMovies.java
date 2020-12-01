package ciolty.VideoDBImplementation.actions.queries;

import ciolty.VideoDBImplementation.actions.MovieAction;
import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.ArrayList;
import java.util.List;

public final class QueryMovies extends QueryVideo implements MovieAction {
    @Override
    protected List<VideoData> getVideosWithFilter() {
        List<VideoData> videos = new ArrayList<>(getAllMoviesWithFilter(
                new VideoFilter(year, genre)));
        return videos;
    }

    @Override
    public String execute() {
        List<String> videosTitles = getVideosTitlesWithFilterSortedAndTrimmed();
        return "Query result: " + videosTitles;
    }
}
