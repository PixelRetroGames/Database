package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.entities.UserData;

import java.util.ArrayList;
import java.util.List;

public abstract class VideoAction
        extends UserAction
        implements MovieAction, SeriesAction {
    protected final String getFirstUnwatchedVideo(final UserData userData) {
        String firstUnwatchedMovie = getFirstUnwatchedMovie(userData);
        String firstUnwatchedSeries = getFirstUnwatchedSeries(userData);

        if (firstUnwatchedMovie == null && firstUnwatchedSeries == null) {
            return null;
        } else {
            return firstUnwatchedMovie == null ? firstUnwatchedSeries : firstUnwatchedMovie;
        }
    }

    protected final List<String> getUnwatchedVideosOfGenre(final UserData userData,
                                                           final String genre) {
        List<MovieData> unwatchedMovies = getUnwatchedMoviesOfGenre(userData, genre);
        List<SeriesData> unwatchedSeries = getUnwatchedSeriesOfGenre(userData, genre);
        List<String> allVideos = new ArrayList<>();

        unwatchedMovies.forEach(movie -> allVideos.add(movie.getTitle()));
        unwatchedSeries.forEach(series -> allVideos.add(series.getTitle()));

        return allVideos;
    }
}
