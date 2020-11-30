package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.ArrayList;
import java.util.List;

public class QuerySeries extends QueryVideo implements SeriesAction{
    @Override
    protected List<VideoData> getVideosWithFilter() {
        List<VideoData> videos = new ArrayList<>(getAllSeriesWithFilter(new VideoFilter(year, genre)));
        return videos;
    }

    @Override
    public String execute() {
        List<String> videosTitles = getVideosTitlesWithFilterSortedAndTrimmed();
        return "Query result: " + videosTitles;
    }
}
