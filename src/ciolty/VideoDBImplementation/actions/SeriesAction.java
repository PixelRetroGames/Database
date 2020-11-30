package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.SeriesData;
import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.database.Filter;

import java.util.List;

public interface SeriesAction extends VideoDBActionInterface {
    /**
     * @param filter
     * @return List of movies with filter
     */
    default List<SeriesData> getAllSeriesWithFilter(Filter filter) {
        List<SeriesData> series = getUnitOfWork().getSeriesRepository().find(filter);
        return series;
    }

    /**
     * @param userData
     * @return first series that haven't been watched by user
     */
    default String getFirstUnwatchedSeries(final UserData userData) {
        List<SeriesData> unwatchedSeries = getUnwatchedSeries(userData);
        if (unwatchedSeries.isEmpty()) {
            return null;
        }

        SeriesData firstUnwatchedSeries = unwatchedSeries.get(0);
        return firstUnwatchedSeries.getTitle();
    }

    /**
     * @param userData
     * @return List of all the series that haven't been watched by user
     */
    default List<SeriesData> getUnwatchedSeries(UserData userData) {
        /*
        List<SeriesData> unwatchedSeries = getUnitOfWork().getSeriesRepository().find(new Filter() {
            @Override
            public boolean isValid(final Object object) {
                SeriesData seriesData = (SeriesData) object;
                if (userData.getHistory().containsKey(seriesData.getTitle())) {
                    LOGGER.log(Level.WARNING, "User " + userData.getUsername()
                            + " watched series " + seriesData.getTitle());
                    return false;
                }
                LOGGER.log(Level.WARNING, "User " + userData.getUsername()
                        + " didn't watch series " + seriesData.getTitle());
                return true;
            }
        });*/
        List<SeriesData> unwatchedSeries = getUnitOfWork().getSeriesRepository().find(new Filter() {
            @Override
            public boolean isValid(final Object object) {
                return !userData.getHistory().containsKey(((SeriesData) object).getTitle());
            }
        });
        return unwatchedSeries;
    }

    /**
     * @param userData
     * @param genre
     * @return List of all series not seen by user from genre
     */
    default List<SeriesData> getUnwatchedSeriesOfGenre(final UserData userData,
                                                      final String genre) {
        List<SeriesData> unwatchedSeries = getUnitOfWork().getSeriesRepository().find(new Filter() {
            @Override
            public boolean isValid(final Object object) {
                return (!userData.getHistory().containsKey(((SeriesData) object).getTitle())
                        && ((SeriesData) object).getGenres().contains(genre));
            }
        });
        return unwatchedSeries;
    }
}
